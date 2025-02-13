package jpa1;

import javax.persistence.*;

@Entity
@Table( name="flats" )
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "flatId")
    private long id;

    @Column( nullable = false )
    private String district;

    @Column( nullable = false )
    private String address;

    @Column( nullable = false )
    private String area;

    @Column( nullable = false )
    private String bedrooms;

    @Column( nullable = false )
    private String price;

    public Flat(String district, String address, String area, String bedrooms, String price) {
        this.district = district;
        this.address = address;
        this.area = area;
        this.bedrooms = bedrooms;
        this.price = price;
    }

    public Flat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat [id=" + id + ", district=" + district + ", address=" + address + ", area=" + area + ", bedrooms=" + bedrooms + ", price=" + price + "]";
    }
}
