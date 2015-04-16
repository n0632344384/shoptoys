$(document)
		.ready(
				function() {

					// work by only first page with products
					// $(".formAddToBasket")
					// .submit(
					// function(e) {
					// e.preventDefault();
					// var form = $(this);
					// var post_url = form.attr('action');
					// var post_data = form.serialize();
					// // var post_url = form.attr('action') +
					// // '?' + form.serialize();
					// // var post_data = '';
					// // alert("post_data: " + post_data
					// // + "\npost_url: " + post_url);
					//
					// $
					// .ajax({
					// type : 'POST',
					// url : post_url,
					// data : post_data,
					// success : function(data) {
					// alert('hello from ajax #formAddToBasket');
					// $("#basketWrapper")
					// .load(
					// "printBasket",
					// function(
					// responseTxt,
					// statusTxt,
					// xhr) {
					// if (statusTxt == "success") {
					// alert("External content loaded successfully!");
					// }
					// if (statusTxt == "error") {
					// alert("Error: "
					// + xhr.status
					// + ": "
					// + xhr.statusText);
					// }
					// });
					// }
					// });
					//
					// });

					// ********************
					// $(".submitFormAddToBasket").on("submit", function(e) {
					// alert("hello");
					// });
					// $(".formAddToBasket").submit(function(e) {
					// alert("hello");
					// });
					//
					// $(document.body)
					// .on(
					// 'submit',
					// '.formAddToBasket',
					// function(e) {
					// alert('$(document.body).on(submit, .formAddToBasket');
					// });

					$(document.body)
							.on(
									'submit',
									'.formAddToBasket',
									function(e) {
										e.preventDefault();
										var form = $(this);
										var post_url = form.attr('action');
										var post_data = form.serialize();
										// var post_url = form.attr('action') +
										// '?' + form.serialize();
										// var post_data = '';
										// alert("post_data: " + post_data
										// + "\npost_url: " + post_url);

										$
												.ajax({
													type : 'POST',
													url : post_url,
													data : post_data,
													success : function(data) {
														$("#basketWrapper")
																.load(
																		"printBasket",
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

					// ********************
					// $(document.body)
					// .on(
					// 'submit',
					// '.submitFormAddToBasket',
					// function(e) {
					// e.preventDefault();
					// var form = $(this);
					// var post_url = form.attr('action');
					// var post_data = form.serialize();
					// // var post_url = form.attr('action') +
					// // '?' + form.serialize();
					// // var post_data = '';
					// // alert("post_data: " + post_data
					// // + "\npost_url: " + post_url);
					// $
					// .ajax({
					// type : 'POST',
					// url : post_url,
					// data : post_data,
					// success : function(data) {
					// alert('hello from ajax #formAddToBasket');
					// $("#basketWrapper")
					// .load(
					// "printBasket",
					// function(
					// responseTxt,
					// statusTxt,
					// xhr) {
					// if (statusTxt == "success") {
					// alert("External content loaded successfully!");
					// }
					// if (statusTxt == "error") {
					// alert("Error: "
					// + xhr.status
					// + ": "
					// + xhr.statusText);
					// }
					// });
					// }
					// });
					// });
					// ********************
					$(document.body)
							.on(
									'click',
									'.removeBasketProduct',
									function(e) {
										e.preventDefault();
										var form = $(this);
										var post_url = form.attr('href');
										var post_data = form.serialize();
										// alert("\npost_url: " + post_url
										// + "\npost_data: " + post_data);
										$
												.ajax({
													type : 'GET',
													url : post_url,
													data : post_data,
													success : function(data) {
														$("#basket")
																.load(
																		"printBasket",
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
					// ********************

				});
