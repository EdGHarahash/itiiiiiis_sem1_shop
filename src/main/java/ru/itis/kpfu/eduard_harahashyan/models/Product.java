package ru.itis.kpfu.eduard_harahashyan.models;

public class Product {

    private int id;
    private String name;
    private int attack;
    private int defense;
    private int damage;
    private int health;
    private String description;
    private int cost;
    private int quantity;

    public Product(String name, int attack, int defense, int damage, int health, String description, int cost, int quantity) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.health = health;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
    }
    public Product(){
        this.name = "образец";
        this.attack = 1;
        this.defense = 1;
        this.damage = 1;
        this.health = 1;
        this.description = "";
        this.cost = 1;
        this.quantity = 1;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public int getIntQuantity(){return quantity; }

    public String getQuantity() {
        if (quantity == 0) {
            return "готовых для службы нет";
        }
        if (quantity < 5) {
            return "мало";
        }
        if (quantity < 10) {
            return "группа";
        }
        if (quantity < 20) {
            return "стая";
        }
        if (quantity < 50) {
            return "толпа";
        }
        if (quantity < 100) {
            return "орда";
        }
        if (quantity < 250) {
            return "сотня";
        }
        if (quantity < 500) {
            return "туча";
        }
        if (quantity < 1000) {
            return "тьма";
        }
            return "легион";
    }

}
