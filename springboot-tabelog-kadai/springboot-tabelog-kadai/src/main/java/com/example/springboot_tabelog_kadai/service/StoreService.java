package com.example.springboot_tabelog_kadai.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot_tabelog_kadai.entity.Store;
import com.example.springboot_tabelog_kadai.form.StoreEditForm;
import com.example.springboot_tabelog_kadai.form.StoreRegisterForm;
import com.example.springboot_tabelog_kadai.repository.StoreRepository;

@Service
public class StoreService {
	private final StoreRepository storeRepository;
	
	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	
//	店舗登録
	@Transactional
	public void create(StoreRegisterForm storeRegisterForm) {
		System.out.println("void");
		Store store = new Store();
		MultipartFile imageFile = storeRegisterForm.getImageFile();
		
		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
        }
        
        store.setName(storeRegisterForm.getName());                
        store.setDescription(storeRegisterForm.getDescription());
        store.setPrice(storeRegisterForm.getPrice());
        store.setGenre(storeRegisterForm.getGenre());
        store.setPostalCode(storeRegisterForm.getPostalCode());
        store.setAddress(storeRegisterForm.getAddress());
        store.setPhoneNumber(storeRegisterForm.getPhoneNumber());
        store.setHoliday(storeRegisterForm.getHoliday());
        store.setOpenTime(storeRegisterForm.getOpenTime());
        store.setCloseTime(storeRegisterForm.getCloseTime());
                    
        storeRepository.save(store);
    }  
    
//	店舗情報編集
	@Transactional
	public void update(StoreEditForm storeEditForm) {
		Store store = storeRepository.getReferenceById(storeEditForm.getId());
		MultipartFile imageFile = storeEditForm.getImageFile();
		
		if(!imageFile.isEmpty()) {
			String imageName =imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
		}
		
		 store.setName(storeEditForm.getName());                
         store.setDescription(storeEditForm.getDescription());
         store.setPrice(storeEditForm.getPrice());
         store.setGenre(storeEditForm.getGenre());
         store.setPostalCode(storeEditForm.getPostalCode());
         store.setAddress(storeEditForm.getAddress());
         store.setPhoneNumber(storeEditForm.getPhoneNumber());
         store.setHoliday(storeEditForm.getHoliday());
         store.setOpenTime(storeEditForm.getOpenTime());
         store.setCloseTime(storeEditForm.getCloseTime());
                     
         storeRepository.save(store);
	}
	
    // UUIDを使って生成したファイル名を返す
    public String generateNewFileName(String fileName) {
        String[] fileNames = fileName.split("\\.");                
        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();            
        }
        String hashedFileName = String.join(".", fileNames);
        return hashedFileName;
    }     
    
    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {           
        try {
            Files.copy(imageFile.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }          
    }

	
}
