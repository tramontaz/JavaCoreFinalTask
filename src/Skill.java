import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class Skill implements HaveID{

    private static final AtomicLong aLong = new AtomicLong(0);
    private final long id;
    private String name;

    public Skill(String name) {
        id = aLong.incrementAndGet();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
}
