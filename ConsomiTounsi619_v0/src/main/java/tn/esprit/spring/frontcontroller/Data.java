package tn.esprit.spring.frontcontroller;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.entity.Type;
import tn.esprit.spring.entity.Category;

import tn.esprit.spring.entity.Ray;

@Scope(value = "session")
@Controller(value = "data")
@ELBeanName(value = "data")
public class Data {
	
	public Type[] getTypes()
	{
	return Type.values();
	}
	
	public Category[] getCategories()
	{
	return Category.values();
	}
	
	
	
}
