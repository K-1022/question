package com.example.nagoyameshi.service;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;        
        this.passwordEncoder = passwordEncoder;

}
    @Transactional
    public User create(Map<String, String> paymentIntentObject) {
        User user = new User();
        Role role = roleRepository.findByName("ROLE_GENERAL");
        
        Integer userId = Integer.valueOf(paymentIntentObject.get("userId"));
        
         //User user = userRepository.getReferenceById(userId);
        
        String name = (String) paymentIntentObject.get("name");
        String furigana = (String) paymentIntentObject.get("furigana");
        String postalCode = (String) paymentIntentObject.get("postalCode");
        String address = (String) paymentIntentObject.get("address");
        String phoneNumber = (String) paymentIntentObject.get("phoneNumber");
        String email = (String) paymentIntentObject.get("email");
        String password = (String) paymentIntentObject.get("password");
        String consent = (String) paymentIntentObject.get("consent");
        
        user.setName(name);
        user.setFurigana(furigana);
        user.setPostalCode(postalCode);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setConsent(consent);
        user.setRole(role);
        user.setEnabled(false);        
        
        return userRepository.save(user);
    }    
    
    @Transactional
    public void update(UserEditForm userEditForm) {
        User user = userRepository.getReferenceById(userEditForm.getId());
        
        user.setName(userEditForm.getName());
        user.setFurigana(userEditForm.getFurigana());
        user.setPostalCode(userEditForm.getPostalCode());
        user.setAddress(userEditForm.getAddress());
        user.setPhoneNumber(userEditForm.getPhoneNumber());
        user.setEmail(userEditForm.getEmail());      
        
        userRepository.save(user);
    }    
    
 // メールアドレスが登録済みかどうかをチェックする
    public boolean isEmailRegistered(String email) {
        User user = userRepository.findByEmail(email);  
        return user != null;
    }   
    
    // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }   
    
 // ユーザーを有効にする
    @Transactional
    public void enableUser(User user) {
        user.setEnabled(true); 
        userRepository.save(user);
    }    
    
    // メールアドレスが変更されたかどうかをチェックする
    public boolean isEmailChanged(UserEditForm userEditForm) {
        User currentUser = userRepository.getReferenceById(userEditForm.getId());
        return !userEditForm.getEmail().equals(currentUser.getEmail());      
    }
	
}
