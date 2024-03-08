var base = 'https://wujiateavn.com/',
    index = false,
    isMobile = '';
var params = [];

//- - - - - - - - - - - - - - - - -

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.3";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

//- - - - - - - - - - - - - - - - -

var chatbox = document.getElementById('fb-customer-chat');
chatbox.setAttribute("page_id", "116331789807557");
chatbox.setAttribute("attribution", "biz_inbox");

//- - - - - - - - - - - - - - - - -

window.fbAsyncInit = function() {
    FB.init({
        xfbml: true,
        version: 'v13.0'
    });
};
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = 'https://connect.facebook.net/vi_VN/sdk/xfbml.customerchat.js';
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

//- - - - - - - - - - - - - - - - -



//- - - - - - - - - - - - - - - - -

window.FontAwesomeConfig = {
    searchPseudoElements: true
}

//- - - - - - - - - - - - - - - - -

function is_jquery_system() {
    setTimeout(function() {
        if (window.jQuery) {
            $('.openModal').bind('click', function() {
                var that = $(this),
                    isModal = $(that.attr('data-target'));
                isModal.addClass('show').show();
                isModal.find('.closeModal').attr('data-target', that.attr('data-target'));
            });
            $('.closeModal').bind('click', function() {
                var that = $(this),
                    isModal = $(that.attr('data-target'));
                isModal.removeClass('show').hide();
            });
            $.notify_modal_vn = function(msg, status) {
                var id = $('#isNotifyModelSystem'),
                    cls, title = 'Thông Báo';
                if (typeof status == 'undefined') status = 'success';
                if (status == 'success') {
                    cls = 'modal-success';
                }
                if (status == 'warning') {
                    cls = 'modal-warning';
                }
                if (status == 'error') {
                    cls = 'modal-danger';
                }
                id.find('.modal-title').html(title);
                id.find('.modal-dialog').attr('class', 'modal-dialog ' + cls);
                id.find('.modal-body').find('p').empty().html(msg);
                id.addClass('show').show();
            };
            $('.dropdown-vn').bind('click', function() {
                var that = $(this),
                    pr = that.parent().find('.nav-dropdown-vn'),
                    fi = that.find('.i_right');
                if (pr.css('opacity') == 0) {
                    pr.css({
                        height: 'auto',
                        opacity: 1,
                        visibility: 'visible'
                    });
                    fi.removeClass('fa-chevron-right').addClass('fa-chevron-down');
                } else {
                    pr.css({
                        height: 0,
                        opacity: 0,
                        visibility: 'hidden'
                    });
                    fi.addClass('fa-chevron-right').removeClass('fa-chevron-down');
                }
            }).attr('href', 'javascript:void(0);');
            $(".c-sidebar-nav-item").each(function(index) {
                if ($(this).find('a').attr('href') == window.location.href) {
                    $('.c-sidebar-nav-item').removeClass('active-link');
                    $(this).addClass('active-link');
                    if ($(this).hasClass('is-dropdown')) {
                        var drop = $(this).parent().parent().find('.dropdown-vn');
                        drop.click();
                        drop.addClass('active-link');
                    }
                }
            });
            $('#showMenu').bind('click', function() {
                var that = $(this),
                    pr = $('#sidebar'),
                    bd = $('body'),
                    cls = $('.isEvClose');
                pr.css('margin-left', 0);
                cls.css('display', 'flex');
            });
            $('.isEvClose').unbind().bind('click', function() {
                var pr = $('#sidebar');
                $('.isEvClose').css('display', 'none');
                pr.css('margin-left', '-100%');
            });
        } else {
            is_jquery_system();
        }
    }, 300);
}
is_jquery_system();
$.generate_token = function(len) {
    var ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var ID_LENGTH = len | 4;
    var rtn = "";
    for (var i = 0; i < ID_LENGTH; i++) {
        rtn += ALPHABET.charAt(Math.floor(Math.random() * ALPHABET.length));
    }
    return rtn;
};
$.format_value = function(value) {
    value = parseInt(value);
    var valueOld = value;
    var flagS = value < 0;
    value = Math.abs(value).toString();
    var countSize = 0;
    var string = [];
    for (var i = value.length - 1; i >= 0; i--) {
        if (countSize === 3) {
            string.push(".");
            countSize = 0;
        }
        string.push(value[i]);
        countSize++;
    }
    string = string.reverse();
    value = "";
    $.each(string, function(i, item) {
        value += item;
    });
    value = flagS ? ("-" + value) : value;
    return $.isNumeric(valueOld) ? value : "";
};
$.format_phone = function(value, that) {
    value = $.trim(value.toString());
    var specialChars = "!@#$^&%*()+=-[]\/{}|:<>?,.";
    for (var i = 0; i < specialChars.length; i++) {
        value = value.replace(new RegExp("\\" + specialChars[i], "gi"), "");
    }
    var string = [],
        index = 0;
    for (var i = value.length - 1; i >= 0; i--) {
        string.push(value[i]);
        if (index === 2 || index === 5) {
            string.push("-");
        }
        index++;
    }
    string = string.reverse();
    value = "";
    $.each(string, function(i, item) {
        value += item;
    });
    if (that) that.val(value);
    return value;
};
$.fn.isVisible = function() {
    var rect = this[0].getBoundingClientRect();
    return ((rect.height > 0 || rect.width > 0) && rect.bottom >= 0 && rect.right >= 0 && rect.top <= (window.innerHeight || document.documentElement.clientHeight) && rect.left <= (window.innerWidth || document.documentElement.clientWidth));
};

function waiting(type) {
    if (typeof type == 'undefined') type = 'open';
    if (type == 'open') {
        $('#dlg-loading').show();
    }
    if (type == 'close') {
        $('#dlg-loading').hide();
    }
}
$.fn.clearForm = function() {
    var that = $(this);
    that.find('input').val('');
    that.find('textarea').val('');
};
$.fn.checkForm = function() {
    var that = $(this),
        error;
    that.find('input').css({
        'border': '1px solid #e1e1e1'
    });
    $.ajax({
        url: base + 'ajax/checkForm',
        type: 'post',
        data: that.serialize(),
        success: function(results) {
            results = JSON.parse(results);
            if (results.error) {
                if (results.errorID) {
                    error = $('#' + results.errorID);
                    error.css({
                        'border': '1px solid #f52222'
                    });
                } else {
                    error = $('#' + results.errorID);
                    error.css({
                        'border': '1px solid #e1e1e1'
                    });
                }
            }
        }
    });
};

function is_jquery_here() {
    setTimeout(function() {
        if (window.jQuery) {
            $(function() {
                $('.item_ch').bind('click', function() {
                    var that = $(this),
                        thu = $('#ch_right');
                    thu.html(that.find('aside').html());
                    $('.item_ch').removeClass('active_ch');
                    that.addClass('active_ch');
                }).first().click();
                $('.send_NQ').bind('click', function() {
                    var that = $(this),
                        frm = that.attr('data-frm');
                    waiting();
                    $.ajax({
                        url: base + 'ajax/sendNhQ',
                        type: 'post',
                        data: $('#' + frm).serialize(),
                        success: function(result) {
                            result = JSON.parse(result);
                            waiting('close');
                            if (result.error) {
                                $.notify_modal_vn(result.msg, 'error');
                            } else {
                                $('#frm_contact').find('input').val('');
                                $('#frm_contact').find('input').val('');
                                $.notify_modal_vn(result.msg, 'success');
                            }
                        }
                    });
                });
                $('.search-ht').bind('click', function() {
                    var sel = $('#sel-ht').val();
                    if (sel > 0) {
                        $('.cuahang').hide();
                        var ch = $('#list-ht-' + sel);
                        ch.show();
                        ch.find('.ht-item').click();
                    }
                });
                $('.ht-item').bind('click', function() {
                    var that = $(this),
                        id = that.attr('data-id'),
                        map = that.find('.data-map').html();
                    $('.maps_cuahang').html(map);
                }).first().click();
                $('.c_cuahang').bind('click', function() {
                    var that = $(this),
                        th = $('.ch_list'),
                        fa = that.find('code').find('i');
                    if (th.css('display') == 'none') {
                        th.slideDown(300);
                        fa.addClass('fa-angle-up').removeClass('fa-angle-down');
                    } else {
                        th.slideUp(300);
                        fa.removeClass('fa-angle-up').addClass('fa-angle-down');
                    }
                });
                $('.ev-cart-dl').bind('click', function() {
                    var that = $(this),
                        id = that.attr('data-id');
                    $('#dl_id').val(id);
                    $('.ch_list').slideUp(300);
                    $('.ch_sel').empty().append(that.clone());
                });
                $('.footer_fix').bind('click', function() {
                    var tb = $('.footer_form');
                    if (tb.css('opacity') == 0) {
                        tb.css({
                            visibility: 'visible',
                            opacity: 1,
                            left: '0px'
                        });
                    }
                });
                $('.close_footer_form').bind('click', function() {
                    var tb = $('.footer_form');
                    tb.css({
                        visibility: 'hidden',
                        opacity: 0,
                        right: '-100%'
                    });
                });
                $(".slider_list").slick({
                    dots: false,
                    infinite: true,
                    slidesToShow: 4,
                    slidesToScroll: 2,
                    prevArrow: $('.prev_hot'),
                    autoplay: true,
                    autoplaySpeed: 4500,
                    nextArrow: $('.next_hot'),
                    responsive: [{
                        breakpoint: 600,
                        settings: {
                            slidesToShow: 2,
                            slidesToScroll: 1
                        }
                    }, {
                        breakpoint: 900,
                        settings: {
                            slidesToShow: 3,
                            slidesToScroll: 1
                        }
                    }, ]
                });

                function telephoneCheck(str) {
                    var isphone = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/.test(str);
                    return isphone;
                }
                $('#send_contact').bind('click', function() {
                    waiting();
                    $.ajax({
                        url: base + 'ajax/sendContact',
                        type: 'post',
                        data: $('#frm_contact').serialize(),
                        success: function(result) {
                            result = JSON.parse(result);
                            waiting('close');
                            if (result.error) {
                                $.notify_modal_vn(result.msg, 'error');
                            } else {
                                $('#frm_contact').find('input').val('');
                                $('#frm_contact').find('input').val('');
                                $.notify_modal_vn(result.msg, 'success');
                            }
                        }
                    });
                });
            });
            $(function() {
                function showImages(el) {
                    var windowHeight = jQuery(window).height(),
                        cls = '.' + el;
                    $(cls).each(function() {
                        var thisPos = $(this).offset().top;
                        var topOfWindow = $(window).scrollTop();
                        if (topOfWindow + windowHeight - 70 > thisPos) {
                            var source = $(this).attr('data-src');
                            $(this).attr('src', source);
                            $(this).removeAttr('data-src').removeClass(el);
                            $(this).removeAttr('data-src');
                        }
                    });
                }
                $(document).ready(function() {
                    showImages('lazy');
                });
                $(window).scroll(function() {
                    showImages('lazy');
                });
            });
            $(function() {
                (function poll() {
                    $.ajax({
                        url: base + 'ajax/tracking?_' + $.now(),
                        type: "GET",
                        success: function(response) {},
                        dataType: "json",
                        complete: setTimeout(function() {
                            poll()
                        }, 8000),
                        timeout: 1000
                    })
                })();
            });
            $(function() {
                var menu = $('#slide-out'),
                    bd = $('body');
                $('.btn_bars').bind('click', function() {
                    bd.css('overflow', 'hidden');
                    menu.css({
                        visibility: 'visible',
                        opacity: 1,
                        right: 0
                    })
                });
                $('.div_close').bind('click', function() {
                    bd.css('overflow-y', 'auto');
                    menu.css({
                        visibility: 'hidden',
                        opacity: 0,
                        right: '100%'
                    })
                });
                $('.js_scroll').bind('click', function() {
                    var that = $(this),
                        fl = $('#' + that.attr('data-s'));
                    $('html, body').animate({
                        scrollTop: (fl.offset().top - 60)
                    }, 'slow');
                });
                $('.isScroll1').bind('click', function() {
                    $('html, body').animate({
                        scrollTop: ($('#pro_home').offset().top)
                    }, 'slow');
                });
                $(window).on("scroll", function() {
                    if ($(window).scrollTop() > 191) {
                        $(".ev_fixed_top").addClass('fixed');
                    } else {
                        $('.ev_fixed_top').removeClass('fixed');
                    }
                });
                $('.openNav').bind('click', function() {
                    var ul = $(this).parent().find('.side-nav-2');
                    if (ul.css('display') == 'none') {
                        $('.openNav').parent().parent().find('.side-nav-2').hide();
                        $('.openNav').removeClass('fa-angle-up').addClass('fa-angle-down');
                        ul.slideDown(300);
                        $(this).addClass('fa-angle-up').removeClass('fa-angle-down');
                    } else {
                        $(this).removeClass('fa-angle-up').addClass('fa-angle-down');
                        ul.slideUp(300);
                    }
                });
                $('.openNav2').bind('click', function() {
                    var ul = $(this).parent().find('.side-nav-3');
                    if (ul.css('display') == 'none') {
                        $('.openNav2').parent().parent().find('.side-nav-3').hide();
                        $('.openNav2').addClass('fa-chevron-down').removeClass('fa-chevron-up');
                        ul.slideDown(300);
                        $(this).addClass('fa-chevron-up').removeClass('fa-chevron-down');
                    } else {
                        $(this).addClass('fa-chevron-down').removeClass('fa-chevron-up');
                        ul.slideUp(300);
                    }
                });
                setTimeout(function() {
                    $('.lines_1').dotdotdot({});
                    $('.lines_2').dotdotdot({});
                    $('.lines_3').dotdotdot({});
                    $('.lines_4').dotdotdot({});
                    $('.lines_5').dotdotdot({});
                }, 100);
                $('.search-submit').on('click', function() {
                    var text = $(this).parent().find('.search-field').val();
                    if (text) {
                        text = text.toLowerCase();
                        text = text.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
                        text = text.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
                        text = text.replace(/ì|í|ị|ỉ|ĩ/g, "i");
                        text = text.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
                        text = text.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
                        text = text.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
                        text = text.replace(/đ/g, "d");
                        text = text.replace(/!|@|\$|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\'| |\"|\&|\#|\[|\]|~/g, "-");
                        text = text.replace(/-+-/g, "-");
                        text = text.replace(/^\-+|\-+$/g, "");
                        text = text.replace(' ', '-');
                    }
                    if (text) window.location = base + 'tim-kiem/' + text
                });
                $(".search-field").keyup(function(e) {
                    if (e.keyCode == 13) {
                        $(this).parent().find('.search-submit').click();
                    }
                });
                $('.tablinks').bind('click', function() {
                    var that = $(this),
                        allTab = $('.tabcontent'),
                        isTab = $('#' + that.attr('data-id'));
                    $('.tablinks').removeClass('img_active');
                    $('.pro_tab_detail').hide();
                    if (isTab.css('display') == 'none') {
                        isTab.show();
                        that.addClass('img_active');
                    }
                }).first().click();
            });
        } else {
            is_jquery_here();
        }
    }, 300);
}
is_jquery_here();

function is_cart_jquery_here() {
    setTimeout(function() {
        if (window.jQuery) {
            $(function() {
                $.fn.updateNumberCart = function() {
                    var that = $(this);
                    $.ajax({
                        url: base + 'cart/changeNumberCar',
                        type: 'post',
                        data: {
                            number: that.val(),
                            id: that.attr('data-id'),
                            color: that.attr('data-color')
                        },
                        success: function(result) {
                            result = JSON.parse(result);
                            if (!result.error) {
                                $('#_total_cart_' + result.id).html(result._total_one);
                                $('#order-total').html(result._total_all);
                            } else {}
                        }
                    });
                };
                $.fn.deleteOneCart = function() {
                    var that = $(this);
                    that.bind('click', function() {
                        var code = $(this).attr('data-code');
                        $.ajax({
                            url: base + 'cart/deleteOneCart',
                            type: 'post',
                            data: {
                                code: code
                            },
                            success: function(result) {
                                result = JSON.parse(result);
                                if (!result.error) {
                                    $.fn.loadListCarts(result.rows);
                                    $('.pop_total').find('span').html($.format_value(result.totalAll) + ' đ');
                                    $('.rs-carts-total').find('label').html('Cộng (' + result.count + ' món)');
                                    $('.rs-carts-total').find('code').html($.format_value(result.totalAll) + ' đ');
                                }
                            }
                        });
                    });
                };
                $('.minus').bind('click', function() {
                    var sl = $('#SL_cart'),
                        valSl = sl.val();
                    if (valSl > 1) valSl--;
                    sl.val(valSl);
                    $('.addCart').attr('data-number', valSl);
                });
                $('.plus').bind('click', function() {
                    var sl = $('#SL_cart'),
                        valSl = sl.val();
                    valSl++;
                    sl.val(valSl);
                    $('.addCart').attr('data-number', valSl);
                });
                $('.change-val-span').bind('click', function() {
                    var i_action = $(this).attr('data-type');
                    var sl = $('#val-span'),
                        valSl = sl.val();
                    if (i_action == 1) {
                        if (valSl > 0) {
                            valSl--;
                        }
                    } else {
                        valSl++;
                    }
                    $('.val-span').html(valSl);
                    sl.val(valSl);
                });
                $.fn.changePriceFrm = function() {
                    var that = $(this);
                    that.bind('click', function() {
                        $.ajax({
                            url: base + 'cart/updatePriceFrm',
                            type: 'post',
                            data: $('#frm_pp_cart').serialize(),
                            success: function(results) {
                                results = JSON.parse(results);
                                if (results._price) {
                                    $('#rs_total_price').html($.format_value(results._price) + ' đ'); /*$('.pop_total').find('span').html($.format_value(results._price)+' đ');*/
                                    $('#ok-update-cart').find('code').html($.format_value(results._price) + ' đ');
                                }
                                $('.html_topping').html(results._html_topping);
                                $('.html_size').html(results._size);
                            }
                        });
                    });
                };
                $('.change-price-frm').changePriceFrm();
                $.fn.loadListCarts = function(data) {
                    var listCarts = $('.list_carts');
                    listCarts.empty();
                    if (data) {
                        var item = $('#list_carts').find('.item_cart').first().clone();
                        $.each(data, function(index, val) {
                            var temp = item.first().clone();
                            temp.attr('data-code', val.code);
                            temp.attr('id', '_item_' + val.code);
                            temp.find('.uk-label').html(val.i);
                            temp.find('aside').find('label').html(val._title);
                            temp.find('span').html(val._html_topping);
                            temp.find('code').html(val._total);
                            temp.find('.remove-cart').attr('data-code', val.code);
                            listCarts.append(temp);
                        });
                        $('.changeCart').eventChangeCart();
                        $('.remove-cart').deleteOneCart();
                    }
                };
                $('#ok-update-cart').bind('click', function() {
                    $.ajax({
                        url: base + 'cart/updateCart',
                        type: 'post',
                        data: $('#frm_pp_cart').serialize(),
                        success: function(results) {
                            results = JSON.parse(results);
                            if (results.error) {
                                $.notify_modal_vn(result.msg, 'error');
                            } else {
                                if (results.rows) {
                                    $.fn.loadListCarts(results.rows);
                                }
                                $('.rs-carts-total').find('label').html('Cộng (' + results.count + ' món)');
                                $('.rs-carts-total').find('code').html($.format_value(results.totalAll) + ' đ');
                                $('.pop_total').find('span').html($.format_value(results.totalAll) + ' đ');
                                $.notify_modal_vn('Cập nhật thành công', 'success');
                                $('.show_order').hide();
                                $('.show_close').hide();
                            }
                        }
                    });
                });
                $('#ok-add-cart').bind('click', function() {
                    $.ajax({
                        url: base + 'cart/addCart',
                        type: 'post',
                        data: $('#frm_pp_cart').serialize(),
                        success: function(results) {
                            results = JSON.parse(results);
                            if (results.error) {
                                $.notify_modal_vn(result.msg, 'error');
                            } else {
                                if (results.rows) {
                                    $.fn.loadListCarts(results.rows);
                                }
                                $('.rs-carts-total').find('label').html('Cộng (' + results.count + ' món)');
                                $('.rs-carts-total').find('code').html($.format_value(results.totalAll) + ' đ');
                                $('.pop_total').find('span').html($.format_value(results.totalAll) + ' đ');
                                $('.show_update_order').hide();
                                $('.show_order').hide();
                                $('.show_close').show();
                            }
                        }
                    });
                });
                $.fn.eventChangeCart = function() {
                    $(this).unbind().bind('click', function() {
                        var that = $(this),
                            code = that.attr('data-code');
                        waiting();
                        $.ajax({
                            url: base + 'cart/loadCart',
                            type: 'POST',
                            data: {
                                code: code
                            },
                            success: function(result) {
                                result = JSON.parse(result);
                                waiting('close');
                                if (result.size) {
                                    var th_size = $('#list-size-order');
                                    th_size.empty();
                                    $.each(result.size, function(index, val) {
                                        var check = '';
                                        if (val._def == 1) {
                                            check = 'checked';
                                            $('.html_size').html(val._title);
                                        }
                                        th_size.append('<span><input  class="change-price-frm" ' + check + ' id="is-size-cart-' + val.id + '" value="' + val.id + '" type="radio" name="size_order"><label for="is-size-cart-' + val.id + '">' + val._title + ' (+ ' + $.format_value(val._price) + ' đ)</label></span>');
                                    });
                                    $('.change-price-frm').changePriceFrm();
                                    if (result.info) {
                                        $('.cart_pro').find('img').attr('src', result.info._img);
                                        $('.cart_pro').find('h4').html(result.info._title);
                                        $('.cart_pro').find('span').html(result.info._custom);
                                        $('#id_pro_frm').val(result.info.id);
                                    }
                                    $('.all_input_topping').prop('checked', false);
                                    if (result.list_topping) {
                                        var html_topping = '';
                                        $.each(result.list_topping, function(index, val) {
                                            $('#topping-' + val).prop('checked', true);
                                        });
                                        $('.html_topping').html(result.html_topping);
                                    }
                                    if (result.note) {
                                        $('.cls_note').val(result.note);
                                    }
                                    if (result.number) {
                                        $('#val-span').val(result.number);
                                        $('.val-span').html(result.number);
                                    }
                                    $('#code_frm').val(result.code);
                                    $('#ok-update-cart').find('code').html($.format_value(result.total) + ' đ');
                                    if (result.rows) {
                                        $.fn.loadListCarts(result.rows);
                                    }
                                    $('.rs-carts-total').find('label').html('Cộng (' + result.count + ' món)');
                                    $('.rs-carts-total').find('code').html($.format_value(result.totalAll) + ' đ');
                                    $('.pop_total').find('span').html($.format_value(result.totalAll) + ' đ');
                                    $('#pop_cart').css({
                                        opacity: 1,
                                        visibility: 'visible'
                                    });
                                    $('.show_order').hide();
                                    $('.show_close').hide();
                                    $('.show_update_order').show();
                                } else {
                                    $.notify_modal_vn(result.msg, 'error');
                                }
                            }
                        });
                    });
                };
                $('.changeCart').eventChangeCart();
                $('.addCart').bind('click', function() {
                    var that = $(this),
                        id = that.attr('data-id');
                    waiting();
                    $.ajax({
                        url: base + 'cart/loadProduct',
                        type: 'POST',
                        data: {
                            id: id
                        },
                        success: function(result) {
                            result = JSON.parse(result);
                            waiting('close');
                            if (result.size) {
                                var th_size = $('#list-size-order');
                                th_size.empty();
                                $.each(result.size, function(index, val) {
                                    var check = '';
                                    if (val._def == 1) {
                                        check = 'checked'
                                    }
                                    th_size.append('<span><input  class="change-price-frm" ' + check + ' id="is-size-cart-' + val.id + '" value="' + val.id + '" type="radio" name="size_order"><label for="is-size-cart-' + val.id + '">' + val._title + ' (+ ' + $.format_value(val._price) + ' đ)</label></span>');
                                });
                                $('.change-price-frm').changePriceFrm();
                                if (result.info) {
                                    $('.cart_pro').find('img').attr('src', result.info._img);
                                    $('.cart_pro').find('h4').html(result.info._title);
                                    $('.cart_pro').find('span').html(result.info._custom);
                                    $('#id_pro_frm').val(result.info.id);
                                    $('#rs_total_price').html($.format_value(result.info._price) + ' đ');
                                }
                                $('.all_input_topping').removeAttr('checked');
                                if (result.rows) {
                                    $.fn.loadListCarts(result.rows);
                                }
                                $('.rs-carts-total').find('label').html('Cộng (' + result.count + ' món)');
                                $('.rs-carts-total').find('code').html($.format_value(result.totalAll) + ' đ');
                                $('.pop_total').find('span').html($.format_value(result.totalAll) + ' đ');
                                $('#pop_cart').css({
                                    opacity: 1,
                                    visibility: 'visible'
                                });
                                $('.show_order').show();
                                $('.show_close').hide();
                                $('.show_update_order').hide();
                            } else {
                                $.notify_modal_vn(result.msg, 'error');
                            }
                        }
                    });
                });
                $('.close_pp').bind('click', function() {
                    $('#pop_cart').css({
                        opacity: 0,
                        visibility: 'hidden'
                    });
                });
                $('#submit-order').bind('click', function() {
                    waiting();
                    $.ajax({
                        type: 'post',
                        url: base + 'cart/createBill',
                        data: $('#frm_order').serialize(),
                        success: function(result) {
                            result = JSON.parse(result);
                            waiting('close');
                            if (result.error) {
                                $.notify_modal_vn(result.msg, 'error');
                            } else {
                                $.notify_modal_vn(result.msg, 'success');
                                setTimeout(function() {
                                    window.location.href = base + 'dat-hang-thanh-cong';
                                }, 1500)
                            }
                        }
                    });
                });
            });
        } else {
            is_cart_jquery_here();
        }
    }, 300);
}
is_cart_jquery_here();

//- - - - - - - - - - - - - - - - -



//- - - - - - - - - - - - - - - - -