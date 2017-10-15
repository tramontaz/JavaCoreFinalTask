import java.util.Set;

public class Standard {
    private Set<HaveID> standards;

    public Standard(Set<HaveID> skillSet) {
        this.standards = skillSet;
    }

    public Set<HaveID> getSkillSet() {
        return standards;
    }

    public void setSkillSet(Set<HaveID> skillSet) {
        this.standards = skillSet;
    }
    
    public void addSkillIntoSkillset(Skill skill) {
        standards.add(skill);
    }
    
    public HaveID getSkillFromSkillSet(long id) {
        HaveID standardThatWillBeReturn = null;
        for (HaveID standard : standards) {
            if (standard.getId() == id) standardThatWillBeReturn = standard;
        }return standardThatWillBeReturn;
    }
}
