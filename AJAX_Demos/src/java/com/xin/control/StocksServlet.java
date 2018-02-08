package com.xin.control;

import com.alibaba.fastjson.JSON;
import com.xin.bean.Stock;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by golden on 2016/11/29 0029.
 * 作用：控制股票的相关信息，同前端进行交互；
 */
@WebServlet(
        name = "StocksServlet", urlPatterns = {"/StocksServlet"}
)
public class StocksServlet extends HttpServlet {
    //保存股票对象的map
    private Map<String, Stock> stocks;

    @Override
    public void init(ServletConfig config) throws ServletException {
        stocks = new HashMap<>();
        //创建股票
        Stock szzs = new Stock("300001", "上证指数", 3000.0, 2990.1);
        Stock pfyh = new Stock("000001", "浦发银行", 23.22, 23.50);
        //将两只股票保存在stocks的map中
        stocks.put(szzs.getId(), szzs);
        stocks.put(pfyh.getId(), pfyh);
        //System.out.println(stocks);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //目的：返回两只股票的价格信息
        //1。计算随机数
        //生成一个随机数，用来该改变当前的价格
        double sz = Math.random() * 20;
        double pf = Math.random() * 0.5;
        //通过一个随机数是奇数还是偶数来判断股票上涨还是下跌
        boolean flagsz = ((int) (Math.random() * 10)) % 2 == 0;
        boolean flagpf = ((int) (Math.random() * 10)) % 2 == 0;

        //2。将随机数和股票的当前价格进行加或减的操作，得到新的当前价格
        Stock szzs = stocks.get("300001");  /**/
        Stock pfyh = stocks.get("000001");
        double temp;
        if (flagsz) {
            temp = szzs.getNow() + sz;
        } else {
            temp = szzs.getNow() - sz;
        }
        temp = (int) (temp * 100) / 100.0;     //需要对新的当前价格进行截取，只保留小数点后两位
        szzs.setNow(temp);
        if (flagpf) {
            temp = pfyh.getNow() + pf;
        } else {
            temp = pfyh.getNow() - pf;
        }
        temp = (int) (temp * 100) / 100.0;
        pfyh.setNow(temp);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //3。返回两只股票的昨天收盘，今天开盘和当前价格
          /*  JSONd的对对象形式
           {
                "000001":{
                    "id":"000001",
                    "name":"浦发银行",
                    "now":23.3,
                    "today":23.5,
                    "yesterday":23.22
                },
                "300001":{
                    "id":"300001",
                    "name":"上证指数",
                    "now":2972.31,
                    "today":2990.1,
                    "yesterday":3000
                }
            }
          * */
        /*采用fastjson.jar包，自带的方法:JSON采用对象的方式来回传数据*/
        String jsonString = JSON.toJSONString(stocks, true);

        // 如果回传表示对象的json，需要在最外层加上一个括号，否则页面解析会出错
        out.print( jsonString );
    }
}
