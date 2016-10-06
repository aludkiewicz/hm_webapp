package se.hm.dtos;

import javax.persistence.*;

/**
 * The instances of this class will be persisted in the underlying DB. They are also presented to the front-end 
 * directly, even though ideally one would likely wish to separate the Database-layer from the Presentation-layer
 * 
 * @author alud
 *
 */
@Entity
@Table(name = "list_result", uniqueConstraints = @UniqueConstraint(columnNames = { "list_result_id" }) )
public class ListResult {

    private int id;
    private String inputList;
    private String sortedList;
    private int iterations;
    private double time;

    public ListResult() {
    }

    @Id
    @GeneratedValue
    @Column(name = "list_result_id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "input")
    public String getInputList() {
        return inputList;
    }

    public void setInputList(String inputList) {
        this.inputList = inputList;
    }
    
    @Column(name = "output")
    public String getSortedList() {
        return sortedList;
    }

    public void setSortedList(String sortedList) {
        this.sortedList = sortedList;
    }
    
    @Column(name = "iterations")
    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
    
    @Column(name = "time")
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}