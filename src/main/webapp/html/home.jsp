<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>H+ Sport</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<header id="home" class="header">
		<nav class="nav" role="navigation">
			<div class="container nav-elements">
				<div class="branding">
					<a href="#home"><img src="images/hpluslogo.svg"
						alt="Logo - H Plus Sports"></a>
				</div>
				<!-- branding -->

				<ul class="navbar">
					<li><a href="home">home</a></li>
					<li><a href="orderHistory">order history</a></li>
					<!-- <li><a href="viewProfile">view my profile</a></li> -->
					<li><a href='<%=response.encodeURL("getProfileDetails")%>'>
						view my profile
					</a></li>
					<li><a href='logout'>logout</a></li>
					<li><a href="redirect">linkedIn</a></li>

				</ul>
				<!-- navbar -->
			</div>
			<!-- container nav-elements -->
		</nav>
	</header>

	<fmt:setBundle basename="applicationResuorces" var="message" />
	<section id="orders" class="section">
		<c:if test="${requestScope.orders!=null}">
		<div class="container">
			<h2 class="headline"><fmt:message bundle="${message}" key="label.header.orders"></fmt:message></h2>
			<table id="orderHistory">

				<tr>
					<th>Order No.</th>
					<th>Product Name</th>
					<th>Order Date</th>
					<th>Product Image</th>
				</tr>
				<c:forEach items="${requestScope.orders}" var="order" varStatus="loop">
				<tr>
					<td>${loop.count}</td>
					<td>${order.productName}</td>
					<td>${order.orderDate}</td>
					<td><img width="200px" height="150px" src="${order.productImgPath}"></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</section>


	<footer class="footer">
		<div class="container">
			<nav class="nav" role="navigation">
				<div class="container nav-elements">
					<div class="branding">
						<a href="#home"><img src="images/hpluslogo.svg"
							alt="Logo - H Plus Sports"></a>
						<p class="address">
							100 Main Street<br> Seattle, WA 98144
						</p>
					</div>
				</div>
			</nav>
			<p class="legal">H+ Sport is a fictitious brand created by
				lynda.com solely for the purpose of training. All products and
				people associated with H+ Sport are also fictitious. Any resemblance
				to real brands, products, or people is purely coincidental.
				Information provided about the product is also fictitious and should
				not be construed to be representative of actual products on the
				market in a similar product category.</p>
		</div>
		<!-- container -->
	</footer>
	<!-- footer -->




</body>
</html>