package com.example.spring_mvc_lab.controller;

import com.example.spring_mvc_lab.model.Product;
import com.example.spring_mvc_lab.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products
    @GetMapping
    public String listProducts(Model model) {

        List<Product> products = productService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("title", "Daftar Produk");
        model.addAttribute("totalProducts", products.size());

        return "product/list";
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id, Model model) {

        Optional<Product> product = productService.findById(id); // FIXED (tidak static)

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("title", "Detail: " + product.get().getName());
        } else {
            model.addAttribute("error", "Produk dengan ID " + id + " tidak ditemukan");
            model.addAttribute("title", "Produk Tidak Ditemukan");
        }

        return "product/detail";
    }

    // GET /products/category/{category}
    @GetMapping("/category/{category}")
    public String productsByCategory(@PathVariable String category, Model model) {

        List<Product> products = productService.findByCategory(category);

        model.addAttribute("products", products);
        model.addAttribute("title", "Kategori: " + category);
        model.addAttribute("totalProducts", products.size());
        model.addAttribute("selectedCategory", category);

        return "product/list";
    }

    // GET /products/search?keyword=xxx
    @GetMapping("/search")
    public String searchProducts(@RequestParam(required = false) String keyword,
                                 Model model) {

        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "";
        }

        List<Product> products = keyword.isEmpty()
                ? productService.findAll()
                : productService.search(keyword);

        model.addAttribute("products", products);
        model.addAttribute("title", "Hasil Pencarian: " + keyword);
        model.addAttribute("totalProducts", products.size());
        model.addAttribute("keyword", keyword);

        return "product/list";
    }
}
