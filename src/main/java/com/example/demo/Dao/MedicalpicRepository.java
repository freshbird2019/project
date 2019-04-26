package com.example.demo.Dao;

import com.example.demo.Domain.Medicalpic;
import com.example.demo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalpicRepository extends JpaRepository<Medicalpic,Integer> {

    List<Medicalpic> findMedicalpicByUserOrderByUploadtimeDesc(User user);
}
