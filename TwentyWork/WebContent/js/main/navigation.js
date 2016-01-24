$(document).ready(function() {
	$("#toggle-nav").click(function() {
		$("#nav-wrap").toggleClass("opened");
		if ($("#nav-wrap").hasClass("opened")) {
			$("#welcome, #tagline").delay(200).fadeIn(300, "swing");
		} else {
			$("#welcome, #tagline").fadeOut(100, "swing");
		}
	});
});