package com.am.petshop.user.service;

import com.am.petshop.user.exception.ResourceNotFoundException;
import com.am.petshop.user.mapper.UserMapper;
import com.am.petshop.user.response.UserDto;
import com.am.petshop.user.model.User;
import com.am.petshop.user.repository.UserRepository;
import com.am.petshop.user.response.UserInfos;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final UserMapper mapper;
	private List<User> userList;

	public UserService(UserRepository userRepository, UserMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
	public void createNewUsersWithList(List<User> newUsersAsList){
		for (User u: newUsersAsList){
			if (userRepository.existsByUsername(u.getUsername())){
				throw new RuntimeException("Username already exists");
			} else {
				userRepository.save(u);
			}
		}
	}

	@Override
	public UserDto findUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()){
			return mapper.convertToDto(user.get()) ;
		} else {
			throw new ResourceNotFoundException("User not found");
		}
	}

	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		 try {
			 if (userRepository.existsById(id)) {
				 userRepository.deleteUserById(id);
			 } else {
				 throw new ResourceNotFoundException("User not found");
			 }
		 } catch (Exception e){
			 throw new RuntimeException("Error deleting user: " + id, e);
		 }
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
	}

	@Override
	@Transactional
	public UserDto updateUser(UserDto updateUser, Integer id) {
		try {
			User user = findUserById(id);
			if (user!=null){
				user.setFirstname(updateUser.getFirstname());
				user.setLastname(updateUser.getLastname());
				user.setEmail(updateUser.getEmail());
				user.setPhone(updateUser.getPhone());
				user.setUsername(updateUser.getUsername());
				userRepository.save(user);
				return mapper.convertToDto(user);
			} else {
				throw new ResourceNotFoundException("User not found");
			}
		} catch (Exception e){
			throw new RuntimeException("Error updating user: " + id, e);
		}

	}

	@Override
	public List<UserInfos> listOfUsersAsTextDatei (){
		userList = userRepository.findAll();
		List<UserInfos> userInfos;
		userInfos = userList.stream()
				.map(u -> new UserInfos(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPhone()))
				.collect(Collectors.toList());
		String path = "/Users/amiram/projects/petshop/src/main/java/com/am/petshop/file/listOfUsers.txt";
		File file = new File(path);
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
			for (UserInfos uf: userInfos){
				fileOutputStream.write(uf.toString().getBytes());
				fileOutputStream.write(",".getBytes());
				fileOutputStream.write("\n".getBytes());
			}
			fileOutputStream.close();
		} catch (IOException e){
			throw new RuntimeException(e);
		}
		return userInfos;
	}

	@SuppressWarnings("VulnerableCodeUsages")
	@Override
	public List<UserInfos> listOfUsersAsPDF(){

		userList = userRepository.findAll();
		List<UserInfos> userInfos;
		userInfos = userList.stream()
				.map(u -> new UserInfos(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPhone()))
				.collect(Collectors.toList()); //TODO: DOPPELT

		String path = "/Users/amiram/projects/petshop/src/main/java/com/am/petshop/file/listOfUsersAsPDF.pdf";
			try {
				PdfWriter pdfWriter = new PdfWriter(path);
				PdfDocument pdfDocument = new PdfDocument(pdfWriter);
				pdfDocument.addNewPage();
				Document document = new Document(pdfDocument);

				Paragraph title = new Paragraph("List of Users");
				title.setTextAlignment(TextAlignment.CENTER);
				title.setFontSize(18f);
				document.add(title);

				Table table = new Table(4);
				table.addHeaderCell("Firstname");
				table.addHeaderCell("Lastname");
				table.addHeaderCell("Email");
				table.addHeaderCell("Phone");

				for (UserInfos uf: userInfos) {
					table.addCell(uf.getFirstname());
					table.addCell(uf.getLastname());
					table.addCell(uf.getEmail());
					table.addCell(uf.getPhone());
				}
				document.add(table);

				document.close();
			} catch (IOException e) {
				throw new RuntimeException("Error creating PDF document", e);
			}
		return userInfos;
	}
}



