/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author gganuza
 */
@Entity
@Table(name = "readers")
public class Readers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private Long readerId;
    @Column(name = "reader_name")
    private String readerName;

    @Override
    public String toString() {
        return "Readers{" + "readerId=" + readerId + ", readerName=" + readerName + '}';
    }
}
