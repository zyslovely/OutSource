$(function(){
	
	// fade effect when changing sections
	$('a[data-fade]').click(function(e){
		$('<div id="loading-mask" class="hidden"></div>').prependTo('body').fadeIn(function(){
			return true;
		});
	})
	
	// selectmenu
	if ($('.select-wrapper').size () > 0) {
		$('.select-wrapper:not(.wide) select').selectmenu({
			transferClasses: true,
			menuWidth: 154
		});
		$('.select-wrapper.wide select').selectmenu({
			transferClasses: true,
			menuWidth: 214
		});
	}
	
	
	
	$('.contact-form .select-wrapper.product, .contact-form .products-category, .contact-form .products-item').hide();
	
	toggleProductCategory(true);
	$('select.products-category-selector').change(function(){
		toggleProductCategory(false);
	});
	$('select.products-item-selector').change(function(){
		toggleProductItem();
	});
	
	$('#loading-mask').fadeOut('slow', function(){
		$(this).remove();
	});
	
});

function toggleProductCategory (onload) {
	// tomo el indice de la categoria seleccionada
	categorySelectedIndex = $('select.products-category-selector :selected').index();
	
	$('.select-wrapper.product.active').hide().removeClass('active');
	$('.select-wrapper.product:eq(' + categorySelectedIndex + ')').show().addClass('active');
	
	$('.products-category.active').fadeOut().removeClass('active').children('li.active').fadeOut().removeClass('active');
	$('.products-category:eq(' + (categorySelectedIndex - 1) + ')').fadeIn(function(){
		$(this).addClass('active');
		toggleProductItem();
	});
	
	return true;
	
}

function toggleProductItem (onload) {
	
	categorySelectedIndex = $('.products-category-selector :selected').index();
	productSelectedIndex = $('.select-wrapper.product.active select :selected').index();
	
	if (productSelectedIndex > 0) {
		$('.products-category.active .products-item.active').fadeOut().removeClass('active');
		$('.products-category.active .products-item:eq(' + (productSelectedIndex - 1) + ')').fadeIn().addClass('active');
	}
	
}

function mapInit () {
	var latlng = new google.maps.LatLng(-34.462737, -58.524939);
	var myOptions = {
		zoom: 16,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("map"), myOptions);
	
	var image = 'img/map_marker.png';
	
	var beachMarker = new google.maps.Marker({
		position: latlng,
		map: map,
		icon: image
	});

	
}
