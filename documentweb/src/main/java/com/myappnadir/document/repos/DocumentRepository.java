package com.myappnadir.document.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.myappnadir.document.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
