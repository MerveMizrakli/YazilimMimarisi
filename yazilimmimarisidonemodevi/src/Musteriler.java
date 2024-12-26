public class Musteriler implements Observer1 {
    private String name;

    public void User(String name) {
        this.name = name;
    }

    @Override
    public void Gozlemciekle(Observer1 observer) {

    }

    @Override
    public void Gozlemcisil(Observer1 observer) {

    }

    @Override
    public void Guncelleme(String mesaj) {
        System.out.println(name + " için bildirim: " + mesaj);
    }

    @Override
    public void Guncelleme() {

    }
}
