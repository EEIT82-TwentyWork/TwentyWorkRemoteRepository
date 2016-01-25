			</div>	
			</div>
</div>	
				
			
			</section>
		</div>	
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/login/Main.js"></script>
<script src="<%=request.getContextPath() %>/js/main/Websocket.js"></script>

<script>
// (function poll() {
//     $.ajax({
<%--         url: '<%= request.getContextPath() %>/BoardServlet/getMyFav', --%>
//         type: "GET",
//         success: function(data) {
//             console.log("polling");
//         },
//         dataType: "json",
//         complete: setTimeout(function() {poll()}, 5000),
//         timeout: 2000
//     })
// })();
</script>
	</body>

<script src="<%=request.getContextPath() %>/js/board/myFavListJS.js"></script>
</html>