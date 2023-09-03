package com.nagarro.exittest.controllers;

import com.nagarro.exittest.impl.ProductServiceImpl;
import com.nagarro.exittest.impl.ReviewServiceImpl;
import com.nagarro.exittest.impl.UserServiceImpl;
import com.nagarro.exittest.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ReviewServiceImpl reviewService;

    /**
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/user/register")
    public User register(@RequestBody User user) throws Exception {
        try {

            // Encode the password
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            Set<UserRole> roles = new HashSet<>();
            Role role = new Role();
            if (user.getEmail().equalsIgnoreCase("raghav.gupta06@nagarro.com")) {
                role.setRoleId(44L);
                role.setRoleName("ADMIN");
            } else {
                role.setRoleId(45L);
                role.setRoleName("NORMAL");

            }

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            roles.add(userRole);
            return this.userService.createUser(user, roles);
        } catch (Exception e) {
            throw new Exception("User with email " + user.getEmail() + " already exists!!");
        }
    }

    /**
     * save a product
     *
     * @param product
     * @return
     */
    @PostMapping("/allProducts")
    public Status saveProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    @GetMapping("/products")
    public List<Product> product(@RequestParam String query) throws Exception {

        try {
            List<Product> products = this.productService.fetchProductByProductNameOrBrandOrProductCode(query);

            return products;
        } catch (Exception e) {
            throw new Exception("Product Not Found!");
        }
    }

    /**
     * @return
     * @throws Exception
     */
    @GetMapping("/allProducts")
    public List<Product> products() throws Exception {

        try {
            List<Product> products = this.productService.findAll();

            return products;
        } catch (Exception e) {
            throw new Exception("Product Not Found!");
        }
    }

    /**
     * @return
     */
    @GetMapping("/user/users")
    public List<User> showUser() {
        return this.userService.findAll();
    }

    /**
     * @return
     */
    @GetMapping("/user/products")
    public List<Product> showProducts() {
        return this.productService.findAll();
    }

    /**
     * @return
     */
    @GetMapping("/user/reviews")
    public List<Review> showReviews() {
        return this.reviewService.findAllReviews();
    }

    /**
     * @return
     */
    @GetMapping("/admin/reviews")
    public List<Review> showAllReviews() {
        return this.reviewService.findAll();
    }

    /**
     * @param product
     * @return
     * @throws Exception
     */
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) throws Exception {
        try {
            System.out.println(product);
            return this.productService.saveProduct(product);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * @param review
     * @return
     * @throws Exception
     */
    @PostMapping("/addReview")
    public Review addReview(@RequestBody Review review) throws Exception {
        try {
            System.out.println(review);
            return this.reviewService.addReview(review);
        } catch (Exception e) {
            throw new Exception("Bad Data");
        }
    }

    /**
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("products/{productId}/showReviews")
    public List<Review> showProductReview(@PathVariable Long productId) throws Exception {
        try {
            return this.reviewService.showProductReview(productId);
        } catch (Exception e) {
            throw new Exception("Product Not Found");
        }
    }

    /**
     * @return
     */
    @GetMapping("home/stats")
    public List<Integer> showStates() {
        List<User> users = this.userService.findAll();
        int totalUsers = users.size();
        int posts = this.reviewService.findAllReviews().size();
        int onlineUsers = 0;
        for (User u : users) {
            if (u.getEnabled()) {
                onlineUsers++;
            }
        }
        List<Integer> stats = new ArrayList<>();
        stats.add(totalUsers);
        stats.add(posts);
        stats.add(onlineUsers);
        return stats;
    }

    /**
     * @param review
     * @return
     * @throws Exception
     */
    @PutMapping("review/approve")
    public Boolean approved(@RequestBody Review review) throws Exception {
        try {
            review.setApproved(true);
            this.reviewService.save(review);
            return true;
        } catch (Exception e) {
            throw new Exception("Something went wrong!!");
        }
    }

    /**
     * @param user
     * @return
     * @throws Exception
     */
    @PutMapping("user/active")
    public User isActive(@RequestBody User user) throws Exception {
        try {
            if (!user.getEnabled())
                user.setEnabled(true);
            else
                user.setEnabled(false);
            return this.userService.save(user);
        } catch (Exception e) {
            throw new Exception("Something went wrong!!");
        }
    }

    /**
     * @param product
     * @return
     */
    @PostMapping("review/request")
    public List<Review> requestReview(@RequestBody Product product) {
        Product p = this.productService.findByProductCode(product.getProductCode());
        if (p != null) {
            return this.reviewService.findByProductId(p.getProductId());
        } else if (p == null) {
            this.productService.saveProduct(product);
        }
        return null;
    }

}
