package com.itheima.admin.model;

import java.util.List;

public class PageBean<T> {

    private List<T> dataList;           //当前页数据列表
    private int firstPage;              //首页
    private int prePage;                //上一页
    private int curPage;                //当前页
    private int nextPage;               //下一页
    private int totalPage;              //总页数，末页
    private int count;                  //总条数
    private int pageSize;               //每页大小，每页显示几条
    private int beginPage;
    private int endPage;

    /**
     * 目标：编写工具方法封装PageBean对象返回
     * @param curPage  当前页
     * @param pageSize 每页大小
     * @param count    总条数
     * @param dataList 当前页数据列表
     * @param <T>
     * @return PageBean
     */
    public static <T> PageBean<T> getPageBean(int curPage,int pageSize,int count,List<T> dataList){
        //创建PageBean
        PageBean<T> pageBean = new PageBean<>();

        //并封装8个属性数据
        //第一个属性：当前页数据列表【从数据库获取，通过参数传递进来】
        pageBean.setDataList(dataList);

        //第二个属性：首页
        pageBean.setFirstPage(1);

        //第三个属性：当前页【从前端获取，通过参数传递进来】
        pageBean.setCurPage(curPage);

        //第四个属性：上一页=当前页-1
        pageBean.setPrePage(curPage-1);

        //第五个属性：下一页=当前页+1
        pageBean.setNextPage(curPage+1);

        //第六个属性：总条数【从数据库获取，通过参数传递进来】
        pageBean.setCount(count);

        //第七个属性：每页大小【由于不同的表分页条数不一样，可以通过参数从外部传递进来】
        pageBean.setPageSize(pageSize);

        //第八个属性：总页数=总条数%每页大小==0?总条数/每页大小:总条数/每页大小+1
        int toalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
        pageBean.setTotalPage(toalPage);

        //第九个和第十个属性
        if (toalPage <= 10) {
            pageBean.setBeginPage(1);
            pageBean.setEndPage(toalPage);
        } else {
            pageBean.setBeginPage(curPage - 5);
            pageBean.setEndPage(curPage+4);

            //无效情况1，beginPage<1
            if (pageBean.getBeginPage() < 1) {
                pageBean.setBeginPage(1);
                pageBean.setEndPage(10);
            }

            //无效情况2：endPage>总页数
            if (pageBean.getEndPage() > toalPage) {
                pageBean.setBeginPage(toalPage - 9);
                pageBean.setEndPage(toalPage);
            }
        }

        //最后返回
        return pageBean;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "dataList=" + dataList +
                ", firstPage=" + firstPage +
                ", prePage=" + prePage +
                ", curPage=" + curPage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                ", count=" + count +
                ", pageSize=" + pageSize +
                '}';
    }

    public PageBean() {
    }

    public PageBean(List<T> dataList, int firstPage, int prePage, int curPage, int nextPage, int totalPage, int count, int pageSize) {
        this.dataList = dataList;
        this.firstPage = firstPage;
        this.prePage = prePage;
        this.curPage = curPage;
        this.nextPage = nextPage;
        this.totalPage = totalPage;
        this.count = count;
        this.pageSize = pageSize;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
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

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
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
