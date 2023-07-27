package com.quanpham.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanpham.demo.BaseRespone.request.CategoryCreateRequest;
import com.quanpham.demo.BaseRespone.request.CategoryRequest;
import com.quanpham.demo.BaseRespone.response.BaseResponse;
import com.quanpham.demo.BaseRespone.response.TicketResponse;
import com.quanpham.demo.models.Category;
import com.quanpham.demo.models.Ticket;
import com.quanpham.demo.services.ICategoryService;
import com.quanpham.demo.services.ITicketService;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse> getAllCategory() {
        return new ResponseEntity<>(this.categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getCategoryById(@PathVariable("id") String id) {

        return ResponseEntity.ok(this.categoryService.getCategoryById(id));
    }

    @GetMapping("/get-by-user")
    public ResponseEntity<BaseResponse> getCategoryByIdUser(@RequestParam("idUser") String idUser) {

        return ResponseEntity.ok(this.categoryService.getCategoryById(idUser));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> addCategory(@Valid @RequestBody CategoryRequest category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCategory(@Valid @RequestBody CategoryCreateRequest categoryReq) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.categoryService.deleteCategory(id));
    }
}
