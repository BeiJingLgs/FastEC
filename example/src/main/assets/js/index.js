$(function () {
    $('.share').click(function () {
        var json = {
            action: "share",
            params: {
                imageUrl:"",
                url:"",
                title:"标题",
                text:"描述"
            }
        };

        FastEC.event(JSON.stringify(json));

    });

    $('.comment').click(function () {

            var json = {
                action: "comment"
            };

            FastEC.event(JSON.stringify(json));

        });
});