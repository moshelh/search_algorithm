import java.util.Objects;

public class Tile {
public String color;
public int val;

public Tile(String _color, int _val){
    this.color=_color;
    this.val=_val;

}
public Tile(Tile other){
this.val= other.val;
this.color = other.color;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return val == tile.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return val+"";
    }
}
