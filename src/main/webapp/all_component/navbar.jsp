<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<div class="container-fluid" style="height: 10px; background-color: #20809d">

</div>
<style>
    @media (min-width: 768px) {
        .col-md-6 {
            -webkit-box-flex: 0;
            -ms-flex: 0 0 50%;
            flex: 0 0 50%;
            max-width: 40%;
        }
    }
    
    .navbar-nav {
        display: flex;
        justify-content: center;
        width: 100%;
    }
</style>
<div class="container-fluid p-3 btn-light">
    <div class="row">
        <div class="col-md-3 text-info">
            <h3><i class="fa-solid fa-book"></i> BOOKHUB</h3>
        </div>
        <div class="col-md-6">
            <form class="form-inline my-2 my-lg-0" method="post" action="search.jsp">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="ch">
                <button class="btn btn-info my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
        <c:if test="${not empty userobj}">
            <div class="col-md-3 d-flex align-items-center justify-content-end">
                <a href="checkout.jsp" class="mr-2"><i class="fas fa-cart-plus fa-2x"></i></a>
                <a href="setting_page.jsp" class="btn btn-success mr-2"><i class="fa-solid fa-user"></i> ${userobj.name}</a>
                <a data-toggle="modal" data-target="#exampleModalCenter" class="btn btn-primary text-white"><i class="fa-solid fa-right-to-bracket"></i> Logout</a>
            </div>
        </c:if>

        <c:if test="${empty userobj}">
            <div class="col-md-3 d-flex align-items-center justify-content-between">
                <!-- Adjusted margins and flex properties for space utilization -->
                <div class="d-flex">
                    <a href="login.jsp" class="btn btn-info mr-1"><i class="fa-solid fa-right-to-bracket"></i> Login</a>
                    <a href="register.jsp" class="btn btn-info mr-1"><i class="fa-solid fa-user-plus"></i> Register</a>
                </div>
                <div class="d-flex">
                    <a href="setting_page.jsp" class="btn btn-info mr-1"><i class="fa-solid fa-gear"></i> Settings</a>
                    <a href="contact_us.jsp" class="btn btn-info"><i class="fa-solid fa-address-book"></i> Contact Us</a>
                </div>
            </div>
        </c:if>
    </div>
</div>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

              
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <div class="mx-auto"> <!-- Centering container -->
            <ul class="navbar-nav">
                <li class="nav-item active-primary">
               <a class="nav-link" href="index.jsp"><i class="fa-solid fa-house"></i> Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active-primary">
                    <a class="nav-link" href="all_recent_books.jsp"><i class="fa-solid fa-book-open"></i> Recent Books</a>
                </li>
                <li class="nav-item active-primary">
                    <a class="nav-link" href="all_new_books.jsp"><i class="fa-solid fa-book-open"></i> New Books</a>
                </li>
                <li class="nav-item active-primary">
                    <a class="nav-link" href="all_old_books.jsp"><i class="fa-solid fa-book-open"></i> Old Books</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<!-- Logout Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">BOOKHUB</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="text-center">
                    <h4>Do you want to logout?</h4>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    <a href="logout" class="btn btn-primary text-white">Yes</a> 
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
<!-- End of Logout Modal -->
