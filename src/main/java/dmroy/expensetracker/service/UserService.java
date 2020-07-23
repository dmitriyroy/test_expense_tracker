package dmroy.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import dmroy.expensetracker.model.*;
import dmroy.expensetracker.repository.UserRepository;
import dmroy.expensetracker.utils.DateUtils;
import dmroy.expensetracker.utils.FileUploader;
import dmroy.expensetracker.utils.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public CustomUser save(CustomUser customUser) {
        return userRepository.save(customUser);
    }
    public CustomUser findById(Integer globalUserId) {
        return userRepository.findById(globalUserId).get();
    }
    public boolean existsById(Integer globalUserId) {
        return userRepository.existsById(globalUserId);
    }
//    public Iterable<GlobalUser> findAll() {
//        return globalUserRepository.findAll();
//    }
    public Iterable findAllById(Iterable<Integer> iterable) {
        return userRepository.findAllById(iterable);
    }
    public long count() {
        return userRepository.count();
    }
    public void deleteById(Integer globalUserId) { userRepository.deleteById(globalUserId); }
    public void delete(CustomUser customUser) {
        userRepository.delete(customUser);
    }
    public void deleteAll() { userRepository.deleteAll(); }

    public CustomUser findByHashValidation(String hash){ return userRepository.findByHashValidation(hash); }
//    public GlobalUser findByMail(String mail){ return globalUserRepository.findByMail(mail.toLowerCase()); }
//    public GlobalUser findByMailAndIsValidMail(String mail, String isValidMail){ return globalUserRepository.findByMailAndIsValidMail(mail.toLowerCase(),isValidMail); }
    public CustomUser findByUsername(String username){ return userRepository.findByUsername(username.toLowerCase()); }
    public CustomUser findByUsernameAndIsValidMail(String username, String isValidMail){ return userRepository.findByUsernameAndIsValidMail(username.toLowerCase(), isValidMail); }
    public CustomUser addGlobalUserMini(String fName, String sName, String email, String password) throws ParseException {
        CustomUser customUser = new CustomUser();
        customUser.setUsername(stringUtils.substrVarchar(email,50).toLowerCase());
        customUser.setFirstName(stringUtils.substrVarchar(fName,150));
        customUser.setSecondName(stringUtils.substrVarchar(sName,150));
        customUser.setEncryptedPassword(passwordEncoder().encode(password.trim()));
        String hashVerification = UUID.randomUUID().toString();
        customUser.setHashValidation(hashVerification);
//        globalUser.setMail(email.trim());
        customUser.setStartRegistrationDttm(new Date());

        this.save(customUser);
        sendLetterVerificationMail(customUser);

        // создаем клиента предприятия T2Studio - UkrCRM
        // это предприятие для администрирования и работы со всеми пользователями UkrCRM
        Client client = clientService.addClient(3, stringUtils.substrVarchar(sName,150),
                                            stringUtils.substrVarchar(fName,150), null,
                                            stringUtils.substrVarchar(email,50).toLowerCase(), null,
                                      null, null, null, null,
                                        null, 1, null, "GlogalUserId="+ customUser.getGlobalUserId(),
                                        null, new Date() , 11);
        if(client != null) {
            List<Client> clients = new ArrayList<>();
            clients.add(client);
            mailingListClientService.addClientsToMailingList(3,clients);
        }
        return customUser;
    }
    public CustomUser setNewPass(CustomUser customUser, String password){
        customUser.setEncryptedPassword(passwordEncoder().encode(password));
        return customUser;
    }
    public CustomUser validationMail(String hash_registration){
        CustomUser customUser = this.findByHashValidation(hash_registration);
        if(customUser != null){
            CustomUser customUser1 = getGlobalUserFull(customUser.getUsername());
            String isValidMail = customUser1.getIsValidMail();
            if(isValidMail == null || !isValidMail.equals("Y")) {
                customUser1.setIsValidMail("Y");
                customUser1.setMailValidationDttm(new Date());
                this.save(customUser1);
                Contact contact = contactService.getGlobalUserMailMain(customUser1.getGlobalUserId());
                if(contact == null){
                    contact = new Contact();
                    contact.setContactData(customUser1.getUsername().toLowerCase());
                    contact.setFlContactMain("Y");
                    contact.setContactTypeId(2);
                    contact = contactService.save(contact);
                    GlobalUserAndContact globalUserAndContact = new GlobalUserAndContact();
                    globalUserAndContact.setContactId(contact.getContactId());
                    globalUserAndContact.setGlobalUserId(customUser1.getGlobalUserId());
                    globalUserAndContactService.save(globalUserAndContact);
                }
            }
            return customUser1;
        }
        return null;
    }
    public void sendLetterVerificationMail(CustomUser customUser){
        StringBuilder subject = new StringBuilder();
        StringBuilder data = new StringBuilder();
        StringBuilder signature = new StringBuilder();

        String baseUrl = appPropertyService.getVerificationUrl();
//        System.out.println("baseUrl = " + baseUrl);

        subject.append("Завершите регистрацию в UkrCRM");
        data.append("Приветствуем, ");
        data.append(customUser.getFirstName());
        data.append(" ");
        data.append(customUser.getSecondName());
        data.append("!");
        data.append("<br>");
        data.append("Для завершения регистрации в UkrCRM необходимо подтвердить Ваш email: ");
        data.append(customUser.getUsername());
        data.append("<br>");
        data.append("Для этого перейдите по ссылке ");
        data.append("<a href=\"");
        data.append(baseUrl);
        data.append(customUser.getHashValidation());
        data.append("\">");
        data.append(baseUrl);
        data.append(customUser.getHashValidation());
        data.append("</a>");

        mailSenderService.sendT2StudioSSL(subject.toString(), data.toString() + signature.toString(),
                customUser.getUsername(), "UkrCRM", "html");

        System.out.println("Отправили письмо : data.toString() " + data.toString());
    }
    public void sendLetterAddToEnterprise(CustomUser customUser, Employee employee, Enterprise enterprise){
        StringBuilder subject = new StringBuilder();
        StringBuilder data = new StringBuilder();
        StringBuilder signature = new StringBuilder();

        String baseUrl = appPropertyService.getLoginUrl();
//        System.out.println("baseUrl = " + baseUrl);

        subject.append("UkrCRM - Вас добавили сотрудником.");
        data.append("Приветствуем, ");
        data.append(customUser.getFirstName());
        data.append(" ");
        data.append(customUser.getSecondName());
        data.append("!");
        data.append("<br>");
        data.append("Вас добавили в предприятие \"");
        data.append(enterprise.getEnterpriseName());
        data.append("\" на должность \"");
        data.append(postService.findById(employee.getPostId()).getPostDescription());
        data.append("\"");
        data.append("<br>");
        data.append("Войдите в систему для начала работы ");
        data.append("<a href=\"");
        data.append(baseUrl);
        data.append("\">");
        data.append(baseUrl);
        data.append("</a>");
        data.append("<br>");
        data.append("<br>");
        data.append("Желаем Вам успехов на новом рабочем месте!");

        mailSenderService.sendT2StudioSSL(subject.toString(), data.toString() + signature.toString(),
                customUser.getUsername(),
                enterprise.getEnterpriseNameShort(),
                "html");

        System.out.println("Отправили письмо : data.toString() " + data.toString());
    }
    public void sendLetterVerificationAndAddToEnterprise(CustomUser customUser, Employee employee, Enterprise enterprise){
        StringBuilder subject = new StringBuilder();
        StringBuilder data = new StringBuilder();
        StringBuilder signature = new StringBuilder();

        String baseUrl = appPropertyService.getVerificationUrl();
//        System.out.println("baseUrl = " + baseUrl);

        subject.append("UkrCRM - Вас добавили сотрудником.");
        data.append("Приветствуем, ");
        data.append(customUser.getFirstName());
        data.append(" ");
        data.append(customUser.getSecondName());
        data.append("!");
        data.append("<br>");
        data.append("Вас добавили в предприятие \"");
        data.append(enterprise.getEnterpriseName());
        data.append("\" на должность \"");
        data.append(postService.findById(employee.getPostId()).getPostDescription());
        data.append("\"");
        data.append("<br>");
        data.append("Подтвердите свой e-mail и войдите в систему для начала работы ");
        data.append("<a href=\"");
        data.append(baseUrl);
        data.append(customUser.getHashValidation());
        data.append("\">");
        data.append(baseUrl);
        data.append(customUser.getHashValidation());
        data.append("</a>");
        data.append("<br>");
        data.append("<br>");
        data.append("Желаем Вам успехов на новом рабочем месте!");

        mailSenderService.sendT2StudioSSL(subject.toString(), data.toString() + signature.toString(),
                customUser.getUsername(),
                enterprise.getEnterpriseNameShort(),
                "html");

        System.out.println("Отправили письмо : data.toString() " + data.toString());
    }
    public void sendResetPasswordMail(CustomUser customUser){
        StringBuilder subject = new StringBuilder();
        StringBuilder data = new StringBuilder();
        StringBuilder signature = new StringBuilder();

        String baseUrl = appPropertyService.getResetPasswordUrl();
//        System.out.println("baseUrl = " + baseUrl);

        subject.append("Сброс пароля для входа в UkrCRM");
        data.append("Приветсвуем, ");
        data.append(customUser.getFirstName());
        data.append(" ");
        data.append(customUser.getSecondName());
        data.append("!");
        data.append("<br>");
        data.append("Для сброса пароля перейдите по ссылке ");
        data.append("<a href=\"");
        data.append(baseUrl);
        data.append(customUser.getHashValidation());
        data.append("\">");
        data.append(baseUrl);
        data.append(customUser.getHashValidation());
        data.append("</a>");

        mailSenderService.sendT2StudioSSL(subject.toString(), data.toString() + signature.toString(),
                customUser.getUsername(), "UkrCRM", "html");

        System.out.println("Отправили письмо сброса пароля : data.toString() " + data.toString());
    }

    public CustomUser getGlobalUserFull(String username){
        CustomUser customUser = this.findByUsername(username);
        Contact mail = contactService.getGlobalUserMailMain(customUser.getGlobalUserId());
        Contact phone = contactService.getGlobalUserPhoneMain(customUser.getGlobalUserId());
        customUser.setMail(mail);
        customUser.setPhone(phone);
        return customUser;
    }

    public CustomUser getGlobalUserFull(CustomUser customUser){
        Contact mail = contactService.getGlobalUserMailMain(customUser.getGlobalUserId());
        Contact phone = contactService.getGlobalUserPhoneMain(customUser.getGlobalUserId());
        customUser.setMail(mail);
        customUser.setPhone(phone);
        return customUser;
    }

    public Map<Integer, CustomUser> getGlobalUserMapByGlobalUserList(List<CustomUser> customUsers){
        Map<Integer, CustomUser> result = new HashMap<>();
        for(CustomUser customUser : customUsers){
            result.put(customUser.getGlobalUserId(), customUser);
        }
        return result;
    }

    public Map<Integer, CustomUser> getGlobalUserMapByGlobalUserIdList(List<Integer> globalUserIds){
        Iterator<CustomUser> globalUserIterator = userRepository.findAllById(globalUserIds).iterator();
        Map<Integer, CustomUser> result = new HashMap<>();
        while(globalUserIterator.hasNext()) {
            CustomUser customUser = globalUserIterator.next();
            result.put(customUser.getGlobalUserId(), getGlobalUserFull(customUser));
        }
        return result;
    }

    private String getProfileFileName(MultipartFile profilePhoto, int globalUserId) {
        String photo;
        String fileName = profilePhoto.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        photo = "profile_image_" + globalUserId + "." + fileType;
        return photo;
    }

    public CustomUser updateGlobalUser(String username, String fName, String sName, String pName,
                                       String mail, String phone, String sex,
                                       String dateBorn, String dateRegistration, MultipartFile profilePhoto) throws ParseException, IOException {

        CustomUser customUser = this.getGlobalUserFull(username);

        String photoFile = "/img/profile_images_empty.png";
        String photo = "/img/profile_images_empty.png";
        String incomeProfilePhoto = profilePhoto.getOriginalFilename().trim();
        if (profilePhoto != null && incomeProfilePhoto.length() > 0) {
            photoFile = getProfileFileName(profilePhoto, customUser.getGlobalUserId());
            fileUploader.uploadFile(profilePhoto, photoFile, "profilePhoto");
            photoFile = photoFile.toLowerCase();
            String uploadProfilePhotoPath = fileUploader.getUploadProfilePhotoPath();
            uploadProfilePhotoPath = uploadProfilePhotoPath.endsWith("/") ? uploadProfilePhotoPath : uploadProfilePhotoPath + "/";
            photo = uploadProfilePhotoPath + photoFile;

            String tmpFile = photo.replace(".","_tmp.");
            BufferedImage originalImage = ImageIO.read(new File(photo));

            int originalImageWidth = originalImage.getWidth();
            int originalImageHeight = originalImage.getHeight();

            int imgSize = originalImageWidth < originalImageHeight ? originalImageWidth : originalImageHeight;
            int x = (originalImageWidth - imgSize) / 2;
            int y = (originalImageHeight - imgSize) / 2;

            originalImage = originalImage.getSubimage( x, y, imgSize, imgSize);

            int width = fileUploader.getProfilePhotoWidth();
            int height = fileUploader.getProfilePhotoHeight();
            ImageIO.write(fileUploader.createResizedCopy(originalImage, width, height, true), "png", new File(tmpFile));

            File f0 = new File(photo);
            File f1 = new File(tmpFile);
            if(f0 != null && f0.exists()){
                if(f1 != null && f1.exists()){
                    f0.delete();
                    f1.renameTo(f0);
                }
            }else if(f1 != null && f1.exists()){
                f1.renameTo(f0);
            }
            customUser.setPhoto("/profile_photo/" + photoFile);
        }

        customUser.setFirstName(stringUtils.substrVarchar(fName,150));
        customUser.setSecondName(stringUtils.substrVarchar(sName,150));
        customUser.setParentName(stringUtils.substrVarchar(pName,150));
        customUser.setSex(sex);
        customUser.setDateBorn(new SimpleDateFormat("yyyy-MM-dd").parse(dateBorn));
        this.updateMailMain(customUser, mail);
        this.updatePhoneMain(customUser, phone);
        this.save(customUser);

        // обновляем клиента предприятия T2Studio - UkrCRM
        // это предприятие для администрирования и работы со всеми пользователями UkrCRM
        Client client = clientService.getClientByGlobalUserFull(customUser);
        if(client != null) {
            clientService.updateClient(client.getClientId(), client.getEnterpriseId(), sName, fName, pName,
                    username, phone,
                    client.getSkype() == null ? null : client.getSkype().getContactData(),
                    client.getViber() == null ? null : client.getViber().getContactData(),
                    client.getTelegram() == null ? null : client.getTelegram().getContactData(),
                    client.getWhatsApp() == null ? null : client.getWhatsApp().getContactData(),
                    sex, client.getClientStatusId(), client.getAddress(), client.getComment(),
                    dateUtils.getDateByString(dateBorn), client.getDateCreate(), 11);
        }

        return customUser;
    }

    public CustomUser updateMailMain(CustomUser customUser, String mail){
        Contact contactMail = customUser.getMail();
        if(mail == null){
            return customUser;
        }

        if(contactMail == null){
            contactMail = new Contact();
            contactMail.setContactData(stringUtils.substrVarchar(mail,255));
            contactMail.setFlContactMain("Y");
            contactMail.setContactTypeId(2);
            contactMail = contactService.save(contactMail);
            GlobalUserAndContact globalUserAndContact = new GlobalUserAndContact();
            globalUserAndContact.setGlobalUserId(customUser.getGlobalUserId());
            globalUserAndContact.setContactId(contactMail.getContactId());
            globalUserAndContactService.save(globalUserAndContact);
            customUser.setMail(contactMail);
        }else{
            contactMail.setContactData(stringUtils.substrVarchar(mail,255));
            contactMail = contactService.save(contactMail);
            customUser.setMail(contactMail);
        }
        return customUser;
    }

    public CustomUser updatePhoneMain(CustomUser customUser, String phone){
        Contact contactPhone = customUser.getPhone();
        if(phone == null){
            return customUser;
        }

        if(contactPhone == null){
            contactPhone = new Contact();
            contactPhone.setContactData(stringUtils.substrVarchar(phone,255));
            contactPhone.setFlContactMain("Y");
            contactPhone.setContactTypeId(1);
            contactPhone = contactService.save(contactPhone);
            GlobalUserAndContact globalUserAndContact = new GlobalUserAndContact();
            globalUserAndContact.setGlobalUserId(customUser.getGlobalUserId());
            globalUserAndContact.setContactId(contactPhone.getContactId());
            globalUserAndContactService.save(globalUserAndContact);
            customUser.setPhone(contactPhone);
        }else{
            contactPhone.setContactData(stringUtils.substrVarchar(phone,255));
            contactPhone = contactService.save(contactPhone);
            customUser.setPhone(contactPhone);
        }
        return customUser;
    }

    public List<CustomUser> findAllByGlobalUserIdInOrderBySecondName(Iterable<Integer> globalUserIdList){
        return userRepository.findAllByGlobalUserIdInOrderBySecondName(globalUserIdList);
    }

}
