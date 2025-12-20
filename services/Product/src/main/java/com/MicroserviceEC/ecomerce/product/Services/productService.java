package com.MicroserviceEC.ecomerce.product.Services;

import com.MicroserviceEC.ecomerce.product.Dtos.ProductRequest;
import com.MicroserviceEC.ecomerce.product.Dtos.productPurchaseRequest;
import com.MicroserviceEC.ecomerce.product.Dtos.productPurchaseResponse;
import com.MicroserviceEC.ecomerce.product.Dtos.productResponse;
import com.MicroserviceEC.ecomerce.product.Execptions.ProductpurchaseException;
import com.MicroserviceEC.ecomerce.product.Mapping.productMapper;
import com.MicroserviceEC.ecomerce.product.Repositories.productRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class productService {

private  final productRepository _productRepo;
private final productMapper mapper;

    public  Integer createProduct(ProductRequest   request ) {


        var product = mapper.toProduct(request);

        return _productRepo.save(product).getId();
    }

    public List< productPurchaseResponse> purchaseProducts(List<productPurchaseRequest> request) {


        var ids = request.stream().map(productPurchaseRequest::product_id).toList();
        var stroedproducts =  _productRepo.findAllByIdInOrderById(ids);

        if (ids.size() != stroedproducts.size()) {
            throw new ProductpurchaseException("one or more products not found");
        }


        var storedRequest = request.
                stream().sorted(Comparator.comparing(productPurchaseRequest::product_id))
                .toList(); // list of product purchase like  i want product of id 1 with quantity 4
                           // and form id 2 quantity 3 .(i want two product four from first and three from second)

        var purchasedProducts = new ArrayList<productPurchaseResponse>();

        for (int i = 0; i < stroedproducts.size(); i++) {
            var product = stroedproducts.get(i); // in database المخزن
            var productrequest = storedRequest.get(i); // request الطلب
            if (product.getAvailableQuantity()<productrequest.quantity()){
                throw new ProductpurchaseException("insufficient stock quantity for product with id ::"+productrequest.product_id() ); // مفيش عدد يكفى
            }

            var updateQuantity =product.getAvailableQuantity()- productrequest.quantity();// لو تمام فهعدل الكمية فى الداتا بيز بعد ما اخدت منها
            product.setAvailableQuantity(updateQuantity);
            _productRepo.save(product);

            purchasedProducts.add(mapper.toProductPurchasedRespone(product,productrequest.quantity())
           // علشان ارجعلة البرودكتس اللى طلبها من المخزن اسمها وسعرها :product
            //  productrequest.quantity() علشان ارجعله العدد اللى طلبه من البرودكت مش من المخزن
            // يعني لو طلب 3 وفى المخزن 10 قبل الطلب يعني كده بعد الطلب بقى فى المخزن 7 فالمفروض ميشوفش اللى فى المخزن فرجعله 3 اللى فى الطلب
            );

        }

        return purchasedProducts;

    }

    public  productResponse findProductById(Integer id) {

        //return mapper.toProductRespone(_productRepo.findById(id));

        return _productRepo.findById(id).map(mapper::toProductRespone).orElseThrow(()->new EntityNotFoundException("" +
                "product not found with id :: "+id));

    }

    public  List<productResponse> findAllProducts() {


        return _productRepo.findAll().stream().map(mapper::toProductRespone).collect(Collectors.toList());

    }
}
