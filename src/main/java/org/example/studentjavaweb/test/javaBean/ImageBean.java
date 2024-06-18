package org.example.studentjavaweb.test.javaBean;

public class ImageBean {
    private int id;
    private String name;
    private int categoryID;

    private String address;

    private String time;

    /**
     * imageInfo 的 单一条目对象
     * @param id imageID
     * @param name imageName
     * @param categoryID imageCategoryID
     * @param address imageAddress
     * @param time imageAddTime
     */
    public ImageBean(int id, String name, int categoryID, String address, String time) {
        this.id = id;
        this.name = name;
        this.categoryID = categoryID;
        this.address = address;
        this.time = time;
    }

    public ImageBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
