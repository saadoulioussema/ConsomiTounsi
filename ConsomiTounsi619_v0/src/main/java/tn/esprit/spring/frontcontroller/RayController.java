package tn.esprit.spring.frontcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.ocpsoft.rewrite.annotation.Join; 
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.controller.CSVData;
import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Notif;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.ProductCategory;
import tn.esprit.spring.entity.Ray;
import tn.esprit.spring.entity.Type;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.sevice.interfece.IProductService;
import tn.esprit.spring.sevice.interfece.IRayInfoService;
import tn.esprit.spring.sevice.interfece.IUserService;

@Scope(value = "session")   
@Controller(value = "rayController")
@ELBeanName(value = "rayController")   
@Join(path = "/", to = "/login5.jsf")
public class RayController {
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	private IRayInfoService rayInfoService;
	@Autowired
	IProductService iProductService;
	@Autowired
	private IUserService userservice; 
	
	public static User USERCONNECTED ;
	
	private String username; 
	private String password;
	private Boolean loggedIn=false;
	   
	private int capacity;  
	private Category categoryray ;        
	private Category categoryrayyy ;  
	private Category categoryray2 ;
	private Type typeray;
	private String ch; 
	private String ch1;
	private List<Ray> rays;
	private Ray selectedRay; 
	private Long employeIdToBeUpdated; 
	private Long idray;
	private Long[] tab;
	private List<Date> 	result2=  new ArrayList<>();
	private Date exprdate;
	private Long barCode;
	private String name;
	private float price;
	private Date exprdate5; 
	private String name5;
	private Long id5;   
    private String msg;
    private String dateproduit;
    private  Part file;
    private String filename;
    private MultipartFile  file1; 
	    
	    
	         
	public String idrays(){          
		String navigateTo = "/productinrays.xhtml?faces-redirect=true";     
		List<Ray> rayss = rayInfoService.getAllRays();
		tab = new Long[rayss.size()];
		int i=0;   
		for(int index = 0; index < rayss.size(); index++){
				Ray ray2=rayss.get(index);
				tab[i]=ray2.getId();
		 		i++;}     
			return navigateTo;
		}      
	public String idrays2(){             
		String navigateTo = "/datedate.xhtml?faces-redirect=true";
		List<Ray> rayss = rayInfoService.getAllRays();
		tab = new Long[rayss.size()];
		int i=0;    
		for(int index = 0; index < rayss.size(); index++){
				Ray ray2=rayss.get(index);
				tab[i]=ray2.getId();
				i++;}
			return navigateTo;
		}   
	public String dologin() {  
	String navigateTo = "null";          
	User user=userservice.getUserByUsernameAndPassword(username, password);
	if (user != null  && user.getRole().equals("ray chief") ) {       
	navigateTo = "/welcomeclient.xhtml?faces-redirect=true";
	loggedIn = true; 
	USERCONNECTED=user;  
	}
	else   
	{FacesMessage facesMessage =new FacesMessage("Login Failed: please check your username/password and try again." );
	FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
	}       
	return navigateTo;}         
	      
	     
	public String doLogout(){ 
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "/login5.xhtml?faces-redirect=true";
	}  
	   
	public String doo(){    

		if (USERCONNECTED.getRole().equals("ray chief") )
		return "/ray.xhtml?faces-redirect=true";
		
			return "/login5.xhtml?faces-redirect=true";
		}

	public String ajouterRay(){        
		String navigateTo="null";
		if(!loggedIn) return "/login5.xhtml"; 
	Ray ray = new Ray(typeray,  categoryray,  capacity);
    ray.setUser(USERCONNECTED);  
    	
    	rayInfoService.addRay(ray);
		navigateTo = "/ray.xhtml?faces-redirect=true";
		return navigateTo;
	} 
	public List<Ray> getRays() { 
		List<Ray> rays = rayInfoService.getAllRays();
		return rays; 
	}            
	public void modifier(Ray ray) 
	{this.setCapacity(ray.getCapacity());  
	this.setTyperay(ray.getTyperay());
	this.setCategoryray(ray.getCategoryray());
	this.setEmployeIdToBeUpdated(ray.getId()); 
	}    
	public String recup(Ray ray) {
		selectedRay.setCapacity(ray.getCapacity());   
		selectedRay.setCategoryray(ray.getCategoryray());
		selectedRay.setTyperay(ray.getTyperay());
		selectedRay.setUser(ray.getUser());

		return "/managebycategory.xhtml"; 
		             
	}     
	    
	public void mettreAjourRay(){       
		Ray e=new Ray(employeIdToBeUpdated,typeray,  categoryray,  capacity );
		   
		rayInfoService.updateRayInfoById(e, employeIdToBeUpdated);     
		}  
	   
	public void deleteray(Long id){ 
		   
		rayInfoService.deleteRay(new Ray(id)); 
		}  
         
   
		//nombre de rayons ( category)     
		public String countRaybycategory(Category categoryray2){       
			
			   ch     ="nombre de rayons de cette category est "+rayInfoService.countRaysbyCategory(categoryray2);
			   return "/managebycategory.xhtml";  
			}  
		
		 
		//afficher rayons par category(category)
				public List<Ray> getAllRayscat(Category categoryray){
			    	     
			    	List<Ray> rays = (List<Ray>) rayInfoService.getRayByCat(categoryray);
			    	return rays; 
			    }  
			
				  public String countProductsInRays( Long idray){
					   
					   ch1 ="nombre de produit par rayon de cet id est "+rayInfoService.countProductsInRays(idray);
					     return  "/productinrays.xhtml";
				  }
				 //afficher all rayons vide
					public List<Ray> getAllRaysvide(){
			    	     
						List<Ray> rays = rayInfoService.getAllRays();
				    	List<Ray> 	result=  new ArrayList<>();
				    	int nbrays=rays.size();	
						Ray ray=new Ray();	
						for(int index = 0; index < nbrays; index++){
							ray=rays.get(index);
							if(rayInfoService.countProductsInRays(ray.getId())==0){
				    		result.add(ray);}
				    	}
						return result; 
				    }  
				//afficher all rayons pleins
					public List<Ray> getAllRaysplein(){
			    	     
						List<Ray> rays = rayInfoService.getAllRays();
				    	List<Ray> 	result=  new ArrayList<>();
				    	int nbrays=rays.size();	
						Ray ray=new Ray();	
						for(int index = 0; index < nbrays; index++){
							ray=rays.get(index);
							if(rayInfoService.countProductsInRays(ray.getId())==ray.getCapacity()){
				    		result.add(ray);}
				    	}
		 		    	return result;
				    	} 
					
					//afficher liste de date expiration d'un id_rayon passé en paramètre
				 	  public List<Date> getListDate( Long idray){ 
				 		 if(result2.isEmpty()==false) 
				 			 return result2;
					    	Ray ray = rayInfoService.getRayById(idray);
					    	List<Product> products1= ray.getProducts(); 
					    Product product=new Product();	
							for(int index = 0; index < products1.size(); index++){
								product=products1.get(index);
					    		result2.add(product.getExprdate());			    	
					    	}
					    	return result2;
					    }
				   
						//afficher Produit ayant date expiré
						 public List<Product> getProductexpr(){
						    	List<Product> product =  rayInfoService.getProductExprdate();
						  
						    	return product;
						 }
						 
							//supprimer les produits de date expiré des rayon
							 public void deleteexpr(){ 
						    	
						    	List<Product> products =  rayInfoService.getProductExprdate();
						    	for(int index = 0; index < products.size(); index++){
									Product product=products.get(index); 
									rayInfoService.deleteNotif(product.getNotif()); 
									iProductService.removeProduct(product.getBarCode());
						    	}  
						     }   
							
					 			//ajouter un produit dans un rayon
						   	    public void addProducttt(){
					 		    	ProductCategory category5= new ProductCategory(id5,name5);
							    	Product product = new Product(barCode,  name,  price,exprdate5,category5);
							    	
						  	    	if (!iProductService.existsById(product.getBarCode())) {
										Category categoryproduct = Category.valueOf( product.getCategory().getName());
					  					List<Ray> rays =  rayInfoService.getRayByCat(categoryproduct);
							  			Ray ray=rays.get(0);
					    					if(rayInfoService.countProductsInRays(ray.getId())<ray.getCapacity())
										{
											rayInfoService.addProductAndAssignToRay(product, ray.getId());
										}
										else {
										 msg= "produit non ajouté! rayon est de capacité maximal";
										}
										
								msg= "Product is successfully added"; 
									}else {
										msg= "Product already exist";
									}
								}
						   	//afficher des notifs de user connecté
						 public List<Notif> affnotif(){ 
								List<Notif> notif=rayInfoService.myNotifications();
										 
						 			if(notif.isEmpty()){
									dateproduit	="pas de produit de date expirée";}
										return notif;	
								} 
						//creation de notif dans la base 
						    public String createnotif(){
						    List<Product> products =  rayInfoService.getProductExprdate();
				 		    	for(int index = 0; index < products.size(); index++){
									Product product=products.get(index);
									rayInfoService.notifyuser(product,product.getRay());
						     	}
						    	dateproduit	=" "; 
						    	return "/notif.xhtml";    
						    }     
						  //upload video <-> ajouter push 3al serveur    
						public void uploadFile() throws IOException {
							Scanner s = new Scanner(file.getInputStream());
							String fileContent = s.useDelimiter("\\A").next();  
							s.close();
							  
							System.out.println(fileContent);  
							 
							Files.write(Paths.get("C:\\"+file.getSubmittedFileName()), fileContent.getBytes(), StandardOpenOption.CREATE);
					  		 } 
	              
					 	//download video           
						  public String downloadFile3( String filename) {               
					 		File ff = new File("./uploads\\"+filename);         
							 
							 Path pathToFile =ff.toPath();                         
						        UrlResource resource = null;    
						        try {
						             resource = new UrlResource(pathToFile.toUri());
						        } catch (MalformedURLException e) {
						            throw new RuntimeException(e);
						        }
						        return "/video.xhtml?faces-redirect=true";
						        }
						//download video22
		
							public ResponseEntity<Object> downloadFile2() throws IOException {
								FileWriter filewriter =  null;
								try {
								 filewriter = new FileWriter(file1.getName());
									
									filewriter.flush();
									
									File file = new File(file1.getName());
								
								InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
								HttpHeaders headers = new HttpHeaders();
								headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
						 		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
								headers.add("Pragma", "no-cache");
								headers.add("Expires", "0");
								
								ResponseEntity<Object> responseEntity = ResponseEntity.ok()          
										.headers(headers)
										.contentLength(file.length())
										.contentType(MediaType
										.parseMediaType("application/txt")).body(resource);
								return responseEntity;
								} catch (Exception e ) {
				return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
			} finally {
				if(filewriter !=null)
					filewriter.close();

			}
							
							}
							
							public ResponseEntity<Object> downloadFile() throws IOException  {
								FileWriter filewriter =  null;
								try {
								CSVData csv1 = new CSVData();
								csv1.setId("1");
								csv1.setName("talk2amareswaran");
								csv1.setNumber("5601");
								
								CSVData csv2 = new CSVData();
								csv2.setId("2");
								csv2.setName("Amareswaran");
								csv2.setNumber("8710");  
								
								List<CSVData> csvDataList = new ArrayList<>();
								csvDataList.add(csv1);
								csvDataList.add(csv2);
								
								StringBuilder filecontent = new StringBuilder("ID, NAME, NUMBER\n");
								for(CSVData csv:csvDataList) {
									filecontent.append(csv.getId()).append(",").append(csv.getName()).append(",").append(csv.getNumber()).append("\n");
								}
								
							  	//String filename = "C:\\talk2amareswaran-downloads\\filedownload\\filedownload\\csvdata.csv";
						String filename = "C:\\Users\\Lenovo\\Documents\\workspace-sts-3.9.4.RELEASE\\Consomi-jsf\\src\\main\\java\\tn\\esprit\\spring\\controller\\csvdata.csv";
						
							 filewriter = new FileWriter(filename);
								filewriter.write(filecontent.toString());  
								filewriter.flush();
								
								File file = new File(filename);
								
								InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
								HttpHeaders headers = new HttpHeaders();
								headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
								headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
								headers.add("Pragma", "no-cache");
								headers.add("Expires", "0");
								
								ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
								return responseEntity;
								} catch (Exception e ) {
									return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
								} finally {
				 					if(filewriter!=null)
					    					filewriter.close();
								}    
							}
	  
					  		 public void vvv() {
							        try {
			                				             FacesContext context = FacesContext.getCurrentInstance();
		                  					               ExternalContext externalContext = context.getExternalContext();
               
							            externalContext.responseReset();
						  	            externalContext.setResponseContentType("image/jpg");
							            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"image.jpg\"");

							            FileInputStream inputStream = new FileInputStream(new File("C:/Users/Lenovo/Desktop/DSC_0001.jpg"));
							            OutputStream outputStream = externalContext.getResponseOutputStream();//C:/Users/Lenovo/Documents/workspace-sts-3.9.4.RELEASE/Consomi-jsf/src/main/webapp/uploads/a.png

							            byte[] buffer = new byte[1024];
							            int length;
							              while ((length = inputStream.read(buffer)) > 0) {
							                outputStream.write(buffer, 0, length);  
							            }

							            inputStream.close();
							            context.responseComplete();

							        } catch (Exception e) {
							            e.printStackTrace(System.out);
							        }
							    }
	
	
	
	 
	
	


	


		
		public MultipartFile getFile1() {
								return file1;
							}
							public void setFile1(MultipartFile file1) {
								this.file1 = file1;
							}
		public String getFilename() {
							return filename;
						}
						public void setFilename(String filename) {
							this.filename = filename;
						}
		public Part getFile() {
								return file;
							}
							public void setFile(Part file) {
								this.file = file;
							}
		public String getDateproduit() {
									return dateproduit;
								}
								public void setDateproduit(String dateproduit) {
									this.dateproduit = dateproduit;
								}
		public String getMsg() {
									return msg;
								}
								public void setMsg(String msg) { 
									this.msg = msg;
								}
		public Long getBarCode() {
									return barCode;
								}
								public void setBarCode(Long barCode) {
									this.barCode = barCode;
								}
								public String getName() {
									return name;
								}
								public void setName(String name) {
									this.name = name;
								}
								public float getPrice() {
									return price;
								}
								public void setPrice(float price) {
									this.price = price;
								}
								public Date getExprdate5() {
									return exprdate5;
								}
								public void setExprdate5(Date exprdate5) {
									this.exprdate5 = exprdate5;
								}
								public String getName5() {
									return name5;
								}
								public void setName5(String name5) {
									this.name5 = name5;
								}
								public Long getId5() {
									return id5;
								}
								public void setId5(Long id5) {
									this.id5 = id5;
								}
	
		public Date getExprdate() {
						return exprdate;
					}
					public void setExprdate(Date exprdate) {
						this.exprdate = exprdate;
					}
		public List<Date> getResult2() {
						return result2;
					}
					public void setResult2(List<Date> result2) {
						this.result2 = result2;
					}
		public Category getCategoryray2() {
					return categoryray2;
				} 
				public void setCategoryray2(Category categoryray2) {
					this.categoryray2 = categoryray2;
				}
		public Long[] getTab() {
					return tab;
				}
				public void setTab(Long[] tab) {
					this.tab = tab;
				}
		public Long getIdray() {
					return idray;
				}


				public void setIdray(Long idray) {
					this.idray = idray;
				}


		public Ray getSelectedRay() {
					return selectedRay;
				}
  

				public void setSelectedRay(Ray selectedRay) {
					this.selectedRay = selectedRay;
				}


		public Category getCategoryrayyy() {
			return categoryrayyy;
		}


		public void setCategoryrayyy(Category categoryrayyy) {
			this.categoryrayyy = categoryrayyy;
		}

 
		public String getCh1() {
			return ch1;
		}
		public void setCh1(String ch1) {
			this.ch1 = ch1;
		}
		public void setCh(String ch) {
			this.ch = ch;
		}


	public String getCh() { 
			return ch;
		}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public int getCapacity() {
		return capacity;
	}
 
   
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}    
            

	public Category getCategoryray() {    
		return categoryray;
	} 


	public void setCategoryray(Category categoryray) {
		this.categoryray = categoryray;
	}


	public Type getTyperay() {
		return typeray;
	}
 

	public void setTyperay(Type typeray) {
		this.typeray = typeray;
	}
	public void setRays(List<Ray> rays) {
		this.rays = rays;
	}


	public Long getEmployeIdToBeUpdated() {
		return employeIdToBeUpdated;
	}


	public void setEmployeIdToBeUpdated(Long employeIdToBeUpdated) {
		this.employeIdToBeUpdated = employeIdToBeUpdated;
	}
	
	
	
	
	
	
	
}
