package za.ac.cput.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.User;
import za.ac.cput.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3315")
@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/list/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.read(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("userName") String userName,
            @RequestParam(value = "picture", required = false) MultipartFile picture) {

        User.Builder newUserBuilder = new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setPhoneNumber(phoneNumber)
                .setUserName(userName);

        // Handle file if it's not null
        if (picture != null && !picture.isEmpty()) {
            try {
                String fileName = picture.getOriginalFilename();
                byte[] fileBytes = picture.getBytes();
                // Save or process the file here
                // Assuming you save the file to a directory and get its URL
                String fileUrl = "/path/to/saved/file/" + fileName;
                newUserBuilder.setPictureUrl(fileUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build(); // Internal Server Error
            }
        } else {
            // If no picture is uploaded, ensure pictureUrl remains NULL
            newUserBuilder.setPictureUrl(null);
        }

        User createdUser = userService.create(newUserBuilder.build());
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User existingUser = userService.read(id);

        if (existingUser != null) {
            existingUser = new User.Builder()
                    .copy(existingUser)
                    .setFirstName(updatedUser.getFirstName())
                    .setLastName(updatedUser.getLastName())
                    .setEmail(updatedUser.getEmail())
                    .setPassword(updatedUser.getPassword())
                    .setPhoneNumber(updatedUser.getPhoneNumber())
                    .setUserName(updatedUser.getUserName())
                    .setPictureUrl(updatedUser.getPictureUrl())
                    .build();

            existingUser = userService.update(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.delete(userId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
