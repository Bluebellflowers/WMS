package com.example.demo.controller;

import com.example.demo.mapper.Financial_informationMapper;
import com.example.demo.model.Financial_information;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private Financial_informationMapper financial_informationMapper;


    //用名称查询某个货物
    @RequestMapping("/queryEmp")
    public String showgetByname(String name, Model model){
            List<Product> list = productService.getByname(name);
            model.addAttribute("list",list);
            return "allProduct";
    }

    //展示所有货物详情
    @RequestMapping("/allProduct")
    public String list(Model model) {
        List<Product> list = productService.queryAllProduct();
        model.addAttribute("list", list);
        return "allProduct";
    }

    //删除某个货物(出库）
    @RequestMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        Product product=productService.queryProductById(id);
        if(product==null){
            return "redirect:/allProduct";
        }else{
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String outtime=dateFormat.format(date);
        product.setOuttime(outtime);
        productService.add_outputProduct(product);
        productService.deleteProductById(id);
        //计算改商品的财务信息
        String intime=product.getIntime();
        var quantity=product.getQuantity();
        try {
            var minutes=calTimeDifference(intime,outtime);
            double money=Cost.per_minute*minutes*quantity;
            money+=Cost.fixrd_fee;
            Financial_information financial_information = new Financial_information();
            var id1=String.valueOf(new Date().getTime());
            financial_information.setId(id1);
            financial_information.setMoney(money);
            financial_information.setProductID(id);
            financial_information.setName(product.getName());
            String time=minutes+"分钟";
            financial_information.setTime(time);
            financial_informationMapper.add(financial_information);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/allProduct";}
    }

    //跳转到修改界面
    @RequestMapping("toUpdateProduct")
    public String toUpdateProduct(Model model, String id) {
        model.addAttribute("Product", productService.queryProductById(id));
        return "updateProduct";
    }
    //修改
    @RequestMapping("/updateProduct")
    public String updateProduct(Model model, Product product) {
        productService.updateProduct(product);
        product = productService.queryProductById(product.getId());
        model.addAttribute("product", product);
        return "redirect:/allProduct";

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //展示入库信息表
    @RequestMapping("/input")
    public String list1(Model model) {
        List<Product> list = productService.queryAllinputProduct();
        model.addAttribute("list", list);
        return "input";
    }

    //跳转到添加
    @RequestMapping("toAddProduct")
    public String toAddProduct() {
        return "addProduct";
    }

    //添加
    @RequestMapping("/addProduct")
    public String addProduct(Product product) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String intime=dateFormat.format(date);
        product.setIntime(intime);
        productService.addProduct(product);
        productService.add_inputProduct(product);
        return "redirect:/input";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //展示出库信息表
    @RequestMapping("/output")
    public String list2(Model model) {
        List<Product> list = productService.queryAlloutputProduct();
        model.addAttribute("list", list);
        return "output";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //展示财务信息
    @RequestMapping("/showFinancial_information")
    public String getFinancialInformation(Model model) {
        List<Financial_information> list=financial_informationMapper.queryAllinformation();
        model.addAttribute("list",list);
        return "/financial_information";
    }


    //跳转到详情页面
    @RequestMapping("/showDetail")
    public String showDetail(Model model, String id) {
        var info=financial_informationMapper.queryinformationByid(id);
        var productID=info.getProductID();
        var product=productService.queryoutProductById(productID);
        model.addAttribute("Financial_information",info);
        model.addAttribute("Product",product);
        return "showDetail";
    }
    //从详情页跳转回去
    @RequestMapping("/tofinancial_information")
    public String tofinancial_information(Model model) {
        List<Financial_information> list=financial_informationMapper.queryAllinformation();
        model.addAttribute("list",list);
        return "/financial_information";
    }

    // 自动计算时间的方法
    public static int calTimeDifference(String time1,String time2) throws ParseException {
        /*分钟差*/
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date fromDate = simpleFormat.parse(time1);
        Date toDate = simpleFormat.parse(time2);
        long from = fromDate.getTime();
        long to = toDate.getTime();
        int minutes = (int) ((to - from) / (1000 * 60));
        return minutes;
//        System.out.println("两个时间之间的分钟差为：" + minutes);
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////

//自定义的类
class Cost{
    static double per_minute=0.01;
    static double fixrd_fee=300;
}

