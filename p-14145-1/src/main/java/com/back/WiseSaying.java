package com.back;

public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    // Getter Sector
    public int getId() { return this.id; }
    public String getContent() { return this.content; }
    public String getAuthor() { return this.author; }

    // Setter Sector
    public void setId(int id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setAuthor(String author) { this.author = author; }
}
