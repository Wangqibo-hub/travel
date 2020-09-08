package com.itheima.travel.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 黑马程序员
 */
public class PageBean<T> implements Serializable {

    private List<T> dataList;            //展现一页数据的联系人列表
    private int firstPage;               //首页
    private int prePage;                 //上一页
    private int nextPage;                //下一页
    private int totalPage;               //总页数，也叫末页
    private int curPage;                 //当前页
    private int count;                   //总条数
    private int pageSize;                //每页大小，每页显示多少条

    @Override
    public String toString() {
        return "PageBean{" +
                "dataList=" + dataList +
                ", firstPage=" + firstPage +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                ", curPage=" + curPage +
                ", count=" + count +
                ", pageSize=" + pageSize +
                '}';
    }

    //设计创建PageBean对象的工具类方法
    public static <T> PageBean<T> getPageBean(int curPage,int pageSize,int count,List<T> dataList){

        //实例对象
        PageBean pageBean = new PageBean();

        //封装8个属性
        //第一个属性dataList【通过调用数据库获取，需要作为方法参数传入进来】
        pageBean.setDataList(dataList);

        //第二个属性firstPage=1
        pageBean.setFirstPage(1);

        //第三个属性curPage【前端传递过来，需要作为方法参数传入进来】
        pageBean.setCurPage(curPage);

        //第四个属性prePage = 当前页-1
        pageBean.setPrePage(curPage-1);

        //第五个属性nextPage = 当前页+1
        pageBean.setNextPage(curPage+1);

        //第六个总条数count【通过调用数据库获取，需要作为方法参数传入进来】
        pageBean.setCount(count);

        //第七个每页大小pageSize【前端传递过来，需要作为方法参数传入进来】
        pageBean.setPageSize(pageSize);

        //第八个总页数totalPage
        //总条数10，每页5条，总页数为2页
        //总条数10，每页3条，总页数为4页
        int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    public PageBean() {
    }

    public PageBean(List<T> dataList, int firstPage, int prePage, int nextPage, int totalPage, int curPage, int count, int pageSize) {
        this.dataList = dataList;
        this.firstPage = firstPage;
        this.prePage = prePage;
        this.nextPage = nextPage;
        this.totalPage = totalPage;
        this.curPage = curPage;
        this.count = count;
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
