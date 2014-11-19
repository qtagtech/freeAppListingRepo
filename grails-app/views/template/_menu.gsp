<div class="col-md-2" style="background: lightgray; border-radius:  0 5px 5px 0">
    <h3>Menu</h3>
    <div>
        <ul class="nav nav-pills nav-stacked" id="stacked-collapse">
            <li <g:if test="${activeMenu==1}">class="active"</g:if> role="presentation">
                <a href="<g:createLink controller="panel" action="index"/>">Home</a>
            </li>
            <li <g:if test="${activeMenu==2}">class="active"</g:if> role="presentation">
                <a data-toggle="collapse" data-parent="#stacked-collapse" href="#one" aria-expanded="true" aria-controls="one" >
                    <div style="width: 76%; display: inline-block">Applications</div> <div style="display: inline-block"><i class="fa fa-caret-square-o-down"></i></div>
                </a>
                <ul id="one" class="nav nav-pills nav-stacked collapse in" <g:if test="${activeMenu==2}">style="background: #f5f5f5; border-radius: 0 0 5px 5px "</g:if>>
                    <li role="presentation">
                        <a href="<g:createLink controller="application" action="create"/>">
                            <div style="padding-left: 10px"><i class="fa fa-caret-right"></i> Create</div>
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="<g:createLink controller="application" action="list"/>">
                            <div style="padding-left: 10px"><i class="fa fa-caret-right"></i> List</div>
                        </a>
                    </li>
                </ul>
            </li>
            <li <g:if test="${activeMenu==3}">class="active"</g:if> role="presentation">
                <a data-toggle="collapse" data-parent="#stacked-collapse" href="#two" aria-expanded="true" aria-controls="two" >
                    <div style="width: 76%; display: inline-block">Campaigns</div> <div style="display: inline-block"><i class="fa fa-caret-square-o-down"></i></div>
                </a>
                <ul id="two" class="nav nav-pills nav-stacked collapse in" <g:if test="${activeMenu==3}">style="background: #f5f5f5; border-radius: 0 0 5px 5px "</g:if>>
                    <li role="presentation">
                        <a href="<g:createLink controller="panel" action="index"/>">
                            <div style="padding-left: 10px"><i class="fa fa-caret-right"></i> Create</div>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>

    </div>
    <br/><br/><br/>
</div>