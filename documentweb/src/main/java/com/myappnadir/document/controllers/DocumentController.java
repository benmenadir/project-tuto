package com.myappnadir.document.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.myappnadir.document.entities.Document;
import com.myappnadir.document.repos.DocumentRepository;

@Controller
public class DocumentController {
private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	DocumentRepository documentRepository;
	
	@RequestMapping("/displayUpload")	
	public String displayUpload(ModelMap modelMap) {
		List<Document> documents = documentRepository.findAll();
		LOG.info("Inside displayUpload() Documents size : " + documents.size());
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") Long id , ModelMap modelMap) {
		
		Document document = new Document();
		document.setId(id);
		document.setName(multipartFile.getOriginalFilename());
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		documentRepository.save(document);

		List<Document> documents = documentRepository.findAll();
		LOG.info("Inside uploadDocument() Documents size : " + documents.size());
		modelMap.addAttribute("documents", documents);
		
		return "documentUpload";
	}
	@RequestMapping("/download")
	public StreamingResponseBody download(@RequestParam("id") Long id, HttpServletResponse response) {
		Document document = documentRepository.findById(id).orElseThrow(null);
		byte[] data = document.getData();
		response.setHeader("Content-Disposition", "attachement;filename=downloaded.jpeg");
		
		
		return outputStream ->{
			outputStream.write(data);
		};
		
	}
	
	
}
