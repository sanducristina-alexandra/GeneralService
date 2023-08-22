package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "climatization_report")
public class ClimatizationReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "power", nullable = false)
    private short power;
    @Column(name = "action_code", nullable = false)
    private short actionCode;

    public ClimatizationReportEntity(Date date, short power, short action) {
        this.date = date;
        this.power = power;
        this.actionCode = action;
    }

    public ClimatizationReportEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public short getPower() {
        return power;
    }

    public void setPower(short power) {
        this.power = power;
    }

    public short getActionCode() {
        return actionCode;
    }

    public void setActionCode(short actionCode) {
        this.actionCode = actionCode;
    }

    @Override
    public String toString() {
        return "ReportId: " + id + "\n Date: " + date +
                "\n Air Conditioning Power: " + power + "\n Action Code: " + actionCode;
    }
}
