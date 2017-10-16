import java.util.Set;

public class Standard {
    private Set<HaveID> standards;

    public Standard(Set<HaveID> entitySet) {
        this.standards = entitySet;
    }

    public Set<HaveID> getEntitySet() {
        return standards;
    }

    public void setEntitySet(Set<HaveID> entitySet) {
        this.standards = entitySet;
    }

    public void addSkillIntoEntitySet(HaveID haveID) {
        standards.add(haveID);
    }

    public HaveID getSkillFromEntitySet(long id) {
        HaveID standardThatWillBeReturn = null;
        for (HaveID standard : standards) {
            if (standard.getId() == id) standardThatWillBeReturn = standard;
        }return standardThatWillBeReturn;
    }
}
