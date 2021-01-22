/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fachriansyahmni.latihanmvcjdb.event;

import edu.fachriansyahmni.latihanmvcjdb.entity.Pelanggan;
import edu.fachriansyahmni.latihanmvcjdb.model.PelangganModel;

/**
 *
 * @author Fachriansyah PC
 */
public interface PelangganListener {

    public void onChange(PelangganModel model);
    public void onInsert(Pelanggan pelanggan);
    public void onDelete();
    public void onUpdate(Pelanggan pelanggan);
}
