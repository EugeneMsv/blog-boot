package ru.text.nastya.domain.entities.base;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
public abstract class Identity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @Override
    public boolean isNew() {
        return this.id == null;
    }


    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && o instanceof Identity && id.equals(((Identity) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
       /* try {
            // TODO: 26.07.2017 Временно, затем убрать рефлексию
            StringBuilder sb = new StringBuilder(getClass().getSimpleName());
            sb.append("{id=").append(id).append(",");
            for (Method method : getClass().getMethods()) {
                if (method.getName().startsWith("get")) {
                    sb.append(StringUtils.uncapitalize(method.getName().split("get")[1]))
                            .append("=")
                            .append(method.invoke(this))
                            .append(",");
                }
            }
            return sb.append("}").toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to print " + getClass());
        }
*/
        StringBuilder sb = new StringBuilder("[");
        return sb.append(getClass().getSimpleName()).append("{id=").append(id).append("}]").toString();

    }
}
