var Pagination = React.createClass({
	propTypes: {
		// max page buttons
		maxPageButtons: React.PropTypes.number,
		pageCount: React.PropTypes.number,
		currentPageIndex: React.PropTypes.number, // start from 1
		
		toPage: React.PropTypes.func.isRequired,
		
		className: React.PropTypes.string,
		
		showStatus: React.PropTypes.bool,
	},
	getDefaultProps: function() {
		return {
			maxPageButtons: 5,
			pageCount: 1, // page count default 1
			currentPageIndex: 1, // page number count from 1
			showStatus: true,
		};
	},
	// make max page buttons is an odd number and at least 3
	getMaxPageButtons: function() {
		var maxPageButtons = this.props.maxPageButtons;
		if (maxPageButtons % 2 == 0) {
			maxPageButtons = maxPageButtons - 1;
		}
		if (maxPageButtons < 3) {
			maxPageButtons = 3;
		}
		return maxPageButtons;
	},
	// get buttons range
	getPageButtonsRange: function() {
		var maxPageButtons = this.getMaxPageButtons();
		// calc the steps from currentPageIndex to maxPageIndex(pageCount)
		var max = 0;
		var availablePageCountFromCurrent = this.props.pageCount - this.props.currentPageIndex;
		var maxButtonCountFromCurrent = Math.floor(maxPageButtons / 2);
		if (availablePageCountFromCurrent >= maxButtonCountFromCurrent) {
			// 
			max = parseInt(this.props.currentPageIndex) + maxButtonCountFromCurrent;
		} else {
			max = parseInt(this.props.currentPageIndex) + availablePageCountFromCurrent;
			// move to min buttons, since no enough available pages to display
			maxButtonCountFromCurrent += (maxButtonCountFromCurrent - availablePageCountFromCurrent);
		}
		// calc the steps from currentPageIndex to first page
		var min = 0;
		var availablePageCountBeforeCurrent = this.props.currentPageIndex - 1;
		if (availablePageCountBeforeCurrent >= maxButtonCountFromCurrent) {
			min = parseInt(this.props.currentPageIndex) - maxButtonCountFromCurrent;
		} else {
			min = 1;
		}
		
		// calc the steps
		if ((max - min) < maxPageButtons) {
			// no enough buttons
			max = min + maxPageButtons - 1;
			max = max > this.props.pageCount ? this.props.pageCount : max;
		}
		
		return {min: min, max: max};
	},
	// render button which jump to first page
	renderFirst: function(buttonsRange) {
		var className = "";
		if (this.props.currentPageIndex == 1) {
			className = "disabled";
		}
		return (<li className={className}>
			<a href="javascript:void(0);" aria-label="First" onClick={this.toFirst}>
				<Glyphicon glyph="fast-backward" />
			</a>
		</li>);
	},
	// render button which jump to previous page section
	renderPreviousSection: function(buttonsRange) {
		var className = "";
		if (this.props.currentPageIndex == 1) {
			className = "disabled";
		}
		return (<li className={className}>
			<a href="javascript:void(0);" aria-label="Previous" onClick={this.toPreviousSection}>
				<Glyphicon glyph="backward" />
			</a>
		</li>);
	},
	// render button which jump to previous page
	renderPrevious: function(buttonsRange) {
		var className = "";
		if (this.props.currentPageIndex == 1) {
			className = "disabled";
		}
		return (<li className={className}>
			<a href="javascript:void(0);" aria-label="Previous" onClick={this.toPrevious}>
				<Glyphicon glyph="chevron-left" />
			</a>
		</li>);
	},
	// render buttons
	renderButtons: function(buttonsRange) {
		var buttons = [];
		for (var index = buttonsRange.min; index <= buttonsRange.max; index++) {
			buttons.push(index);
		}
		var _this = this;
		return buttons.map(function(index) {
			var style = {}; 
			if (index == _this.props.currentPageIndex) {
				style.backgroundColor = "#eee";
			}
			return (<li>
				<a href="javascript:void(0);" onClick={_this.toPage} data-index={index} style={style}>{index}</a>
			</li>);
		});
	},
	// render button which jump to next page
	renderNext: function(buttonsRange) {
		var className = "";
		if (this.props.currentPageIndex == this.props.pageCount) {
			className = "disabled";
		}
		return (<li className={className}>
			<a href="javascript:void(0);" aria-label="Next" onClick={this.toNext}>
				<Glyphicon glyph="chevron-right" />
			</a>
		</li>);
	},
	// render button which jump to next page section
	renderNextSection: function(buttonsRange) {
		var className = "";
		if (this.props.currentPageIndex == this.props.pageCount) {
			className = "disabled";
		}
		return (<li className={className}>
			<a href="javascript:void(0);" aria-label="Next" onClick={this.toNextSection}>
				<Glyphicon glyph="forward" />
			</a>
		</li>);
	},
	// render button which jump to last page
	renderLast: function(buttonsRange) {
		var className = "";
		if (this.props.currentPageIndex == this.props.pageCount) {
			className = "disabled";
		}
		return (<li className={className}>
			<a href="javascript:void(0);" aria-label="Last" onClick={this.toLast}>
				<Glyphicon glyph="fast-forward" />
			</a>
		</li>);
	},
	renderStatus: function() {
		if (this.props.showStatus) {
			return (<div className="pagination-status col-md-2">
				<div>
					Page: {this.props.currentPageIndex} / {this.props.pageCount}
				</div>
			</div>);
		} else {
			return null;
		}
	},
	render: function() {
		var buttonsRange = this.getPageButtonsRange();
		var className = "row" + (this.props.className ? (" " + this.props.className) : "");
		return (<div className={className}>
			{this.renderStatus()}
			<div className="col-md-10 pagination-status-buttons">
				<ul className="pagination">
					{this.renderFirst(buttonsRange)}
					{this.renderPreviousSection(buttonsRange)}
				    {this.renderPrevious(buttonsRange)}
				    {this.renderButtons(buttonsRange)}
				    {this.renderNext(buttonsRange)}
				    {this.renderNextSection(buttonsRange)}
				    {this.renderLast(buttonsRange)}
				</ul>
			</div>
		</div>);
	},
	// get current page index
	getCurrentPageIndex: function(button) {
		return $(button).attr("data-index");
	},
	// jump to first page
	toFirst: function() {
		this.jumpTo(1);
	},
	// jump to previous page section
	toPreviousSection: function() {
		var previousIndex = this.props.currentPageIndex - this.getMaxPageButtons();
		previousIndex = previousIndex < 1 ? 1 : previousIndex;
		this.jumpTo(previousIndex);
	},
	// jump to previous page
	toPrevious: function() {
		var previousIndex = this.props.currentPageIndex - 1;
		this.jumpTo(previousIndex < 1 ? 1 : previousIndex);
	},
	// jump to given page according to event
	toPage: function(evt) {
		this.jumpTo(this.getCurrentPageIndex(evt.target));
	},
	// jump to next page
	toNext: function() {
		var nextIndex = this.props.currentPageIndex + 1;
		this.jumpTo(nextIndex > this.props.pageCount ? this.props.pageCount : nextIndex);
	},
	// jump to next page section
	toNextSection: function() {
		var nextIndex = this.props.currentPageIndex + this.getMaxPageButtons();
		nextIndex = nextIndex > this.props.pageCount ? this.props.pageCount : nextIndex;
		this.jumpTo(nextIndex);
	},
	// jump to last page
	toLast: function() {
		this.jumpTo(this.props.pageCount);
	},
	jumpTo: function(pageIndex) {
		if (this.props.toPage) {
			this.props.toPage(pageIndex);
		}
	}
});