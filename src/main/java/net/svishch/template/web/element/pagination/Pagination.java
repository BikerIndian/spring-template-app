package net.svishch.template.web.element.pagination;

import java.util.ArrayList;
import java.util.List;


/**
 * ver: 1.0.2
 */
public class Pagination {

    private boolean visibility = true;
    private String formatOnclick;
    private int pageNum;
    private int sizePage;
    private boolean previous;
    private boolean next;
    private boolean btnNext = true;
    private Pages pagesNext;
    private Pages pagesPrevious;

    private boolean btnPrevious = true;

    private int totalSize;
    private int endPages;
    public List<Pages> pagesList;

    public Pagination(
            String formatOnclick,
            int pageNum, // Номер страницы
            int sizePage, // Количество страниц
            int totalSize, // Количество всего эдементов
            boolean previous,
            boolean next) {

        this.formatOnclick = formatOnclick;
        this.pageNum = pageNum;
        this.sizePage = sizePage;
        this.previous = previous;
        this.next = next;
        this.totalSize = totalSize;
        this.endPages = (int) Math.ceil((float) totalSize / sizePage);

        initialization();
        // printDebug();
    }


    public Pagination(
            String formatOnclick,
            PaginationParamIn paginationParamIn, // Номер страницы
            int sizePage, // Количество страниц
            int totalSize // Количество всего эдементов
            ) {

        this.formatOnclick = formatOnclick;
        this.pageNum = paginationParamIn.getPageNum();
        this.previous = paginationParamIn.isPrevious();
        this.next = paginationParamIn.isNext();
        this.sizePage = sizePage;
        this.totalSize = totalSize;
        this.endPages = (int) Math.ceil((float) totalSize / sizePage);

        initialization();
        // printDebug();
    }


    private void initialization() {
        int sizeBtnPagination = 5;

        if (sizeBtnPagination > endPages) {
            sizeBtnPagination = endPages;
        }

        check();
        setBtnNext();
        setBtnPrevious();

        pagesList = new ArrayList<>();

        int ii = 0;

        if (3 < pageNum) {
            ii = pageNum - 3;
        }


        if (endPages - sizeBtnPagination + 3 < pageNum) {
            ii = endPages - sizeBtnPagination;
        }

        for (int i = ii; i < sizeBtnPagination + ii; i++) {

            Pages page = new Pages(this.formatOnclick);
            page.pageNum = i + 1;

            if (page.pageNum == pageNum) {
                page.active = true;
            }

            page.sizePage = sizePage;
            pagesList.add(page);
        }
    }

    private void check() {
        if (totalSize <= 0) {
            visibility = false;
        }
    }

    private void setBtnPrevious() {
        this.pagesPrevious = new Pages(formatOnclick);
        setPagesPrevious();
        if (this.pageNum == 1) {
            this.pagesPrevious.setClassCss("page-link-disable");
            pagesPrevious.setFormatOnclick("");
            this.btnPrevious = false;
        }
    }

    private void setBtnNext() {
        this.pagesNext = new Pages(formatOnclick);
        setPagesNext();
        if (this.pageNum == this.endPages) {
            this.pagesNext.setClassCss("page-link-disable");
            pagesNext.setFormatOnclick("");
            this.btnNext = false;

        }
    }


    public void setPagesNext() {

        int pagesNextPageNum = pageNum + 1;
        if (pagesNextPageNum >= this.endPages) {
            pagesNextPageNum = this.endPages;
        }
        pagesNext.pageNum = pagesNextPageNum;
        pagesNext.sizePage = this.sizePage;
    }


    public void setPagesPrevious() {

        int previousPageNum = pageNum - 1;
        if (previousPageNum <= 0) {
            previousPageNum = 1;
        }
        pagesPrevious.pageNum = previousPageNum;
        pagesPrevious.sizePage = this.sizePage;
    }

    public boolean isBtnNext() {
        return btnNext;
    }

    public boolean isBtnPrevious() {
        return btnPrevious;
    }

    public Pages getPagesNext() {
        return pagesNext;
    }

    public Pages getPagesPrevious() {
        return pagesPrevious;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }


    private void printDebug() {
        System.out.println("formatOnclick = " + formatOnclick);
        System.out.println("pageNum = " + pageNum);
        System.out.println("sizePage = " + sizePage);
        System.out.println("previous = " + previous);
        System.out.println("next = " + next);
        System.out.println("totalSize = " + totalSize);
        System.out.println("endPages = " + endPages);
    }

}
