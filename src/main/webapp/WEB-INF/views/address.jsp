<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
        <h1>Add Address</h1>  
       <form:form method="post" action="saveaddress" modelAttribute="address">    
        <table >    
         <tr>    
          <td>id : </td>   
          <td><form:input path="id"  /></td>  
         </tr>    
         <tr>    
          <td>city :</td>    
          <td><form:input path="city" /></td>  
         </tr>   
         <tr>    
          <td>state :</td>    
          <td><form:input path="state" /></td>  
         </tr>
         <tr>    
          <td>pincode :</td>    
          <td><form:input path="pincode" /></td>  
         </tr>   
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>