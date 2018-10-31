package ru.text.nastya.domain.entities.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Identity implements Persistable<String> {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    protected String uuid;

    @Version
    protected Long version;

    @Transient
    @Override
    public boolean isNew() {
        return this.uuid == null && version == null;
    }

    @Override
    public String getId() {
        return getUuid();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return proxyCheck(o) && uuid.equals(((Identity) o).uuid);
    }

    protected boolean proxyCheck(Object o) {
        return o instanceof Identity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
