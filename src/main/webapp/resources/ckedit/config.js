/**
 * ckeditor plugin setter
 */

CKEDITOR.editorConfig = function(config) {
	//config.pasteFromWordRemoveStyles = true;
    //config.enterMode = CKEDITOR.ENTER_DIV;
	config.contentsCss = ['../resources/css/bootstrap.css', '../resources/css/custom_content.css' ];
    config.resize_enabled = true;
    config.toolbar = 'Complex';
    config.toolbar_Simple = [ [ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink', '-', 'About' ] ];
    config.toolbar_Complex = [
            [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
                    'Superscript', 'TextColor', 'BGColor', '-', 'Cut', 'Copy',
                    'Paste', 'Link', 'Unlink', 'Image'],
            [ 'Undo', 'Redo', '-', 'JustifyLeft', 'JustifyCenter',
                    'JustifyRight', 'JustifyBlock' ],
            [ 'Table', 'Smiley', 'SpecialChar', 'PageBreak',
                    'Styles', 'Format', 'Font', 'FontSize','Maximize'] ];
    config.allowedContent=true;
    //config.extraAllowedContent='iframe[*]; ol[*](*); li[*](*); a[*]; span[*](*); div(*)[sytle,*];';
    
};

/**
 * ckeditor plugin setter


CKEDITOR.editorConfig = function(config) {
	//config.pasteFromWordRemoveStyles = true;
    //config.enterMode = CKEDITOR.ENTER_DIV;
	config.contentsCss = ['../resources/css/bootstrap.css', '../resources/css/custom_content.css' ];
    config.resize_enabled = true;
    //config.toolbar = 'Complex';
    config.toolbar_Simple = [ [ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink', '-', 'About' ] ];
    config.toolbar_Complex = [
            [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
                    'Superscript', 'TextColor', 'BGColor', '-', 'Cut', 'Copy',
                    'Paste', 'Link', 'Unlink', 'Image'],
            [ 'Undo', 'Redo', '-', 'JustifyLeft', 'JustifyCenter',
                    'JustifyRight', 'JustifyBlock' ],
            [ 'Table', 'Smiley', 'SpecialChar', 'PageBreak',
                    'Styles', 'Format', 'Font', 'FontSize','Maximize'] ];
   
    
};
 */



