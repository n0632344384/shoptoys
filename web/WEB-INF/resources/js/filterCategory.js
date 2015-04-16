$(document)
		.ready(
				function() {
					$("#formFilterCategory")
							.submit(
									function(e) {
										e.preventDefault();
										var form = $(this);
										var post_url = form.attr('action');
										var post_data = form.serialize();

										$
												.ajax({
													type : 'POST',
													url : post_url,
													data : post_data,
													success : function(data) {
														$("#center")
																.load(
																		"printProducts",
																		function(
																				responseTxt,
																				statusTxt,
																				xhr) {
																			// if
																			// (statusTxt
																			// ==
																			// "success")
																			// {
																			// alert("External
																			// content
																			// loaded
																			// successfully!");
																			// }
																			if (statusTxt == "error") {
																				alert("Error: "
																						+ xhr.status
																						+ ": "
																						+ xhr.statusText);
																			}
																		});
													}
												});
									});
					// **************************************************
					$(document.body)
							.on(
									'click',
									'.pagePagin',
									function(e) {
										e.preventDefault();
										var form = $(this);
										var post_url = form.attr('href');
										var post_data = form.serialize();
										$
												.ajax({
													type : 'POST',
													url : post_url,
													data : post_data,
													success : function(data) {
														$("#center")
																.load(
																		"printProducts",
																		function(
																				responseTxt,
																				statusTxt,
																				xhr) {
//																			if (statusTxt == "success") {
//																				alert("External content loaded successfully!");
//																			}
																			if (statusTxt == "error") {
																				alert("Error: "
																						+ xhr.status
																						+ ": "
																						+ xhr.statusText);
																			}
																		});
													}
												});

									});

				});