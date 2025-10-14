package cartes;

public abstract class Carte {

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}