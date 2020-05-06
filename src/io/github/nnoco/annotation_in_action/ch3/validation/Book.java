package io.github.nnoco.annotation_in_action.ch3.validation;

/**
 * 책 정보 클래스
 */
public class Book {
    @NotNull(message = "title은 필수 항목입니다.")
    private String title;

    @NotNull(message = "author는 필수 항목입니다.")
    private String author;

    private Integer price;

    public Book(String title, String author, Integer price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
