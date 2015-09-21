(function($j) {
    $j.fn.extend({
        PrintWindow: function() {
            return this.bind('click', function() {
                window.print();
                return false;
            });
        }
    });
})(jQuery);