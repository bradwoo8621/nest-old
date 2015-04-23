// modal confirm dialog
var ModalConfirmDialog = React.createClass({
	propTypes: {
		className: React.PropTypes.string,
		zIndex: React.PropTypes.number
	},
	getInitialState: function() {
		return {
			visible: false,
			title: null,
			messages: null,
			callback: null,
		};
	},
	componentDidUpdate: function(prevProps, prevState) {
		this.setZIndex();
	},
	componentDidMount: function () {
		this.setZIndex();
	},
	render: function() {
		if (!this.state.visible) {
			return null;
		}
		
		var messages = this.state.messages;
		if (!this.state.messages.length) {
			messages = [this.state.messages];
		}
		return (<Modal className={this.props.className} bsStyle="danger" title={this.state.title} 
			onRequestHide={this.hide} backdrop="static">
					<div className="modal-body" ref="body">
						{messages.map(function(element) {
							return <h5>{element}</h5>;
						})}
					</div>
					<div className="modal-footer">
						<Button bsStyle="danger" onClick={this.callback}>OK</Button>
						<Button onClick={this.hide}>Cancel</Button>
					</div>
		</Modal>);
	},
	callback: function() {
		if (this.state.callback) {
			if (this.state.callbackParameters !== undefined) {
				this.state.callback(this.state.callbackParameters);
			} else {
				this.state.callback();
			}
		}
	},
	show: function(title, messages, confirmCallback, callbackParameters) {
		var state = {
			visible: true,
			title: title,
			messages: messages,
			callback: confirmCallback,
		};
		if (callbackParameters !== undefined) {
			state.callbackParameters = callbackParameters;
		}
		this.setState(state);
	},
	hide: function() {
		this.setState({visible: false});
	},
	setZIndex: function() {
		if (this.props.zIndex != undefined) {
			var div = $(React.findDOMNode(this.refs.body)).closest(".modal");
			if (div.length > 0) {
				div.css({"z-index": this.props.zIndex * 1 + 1});
				div.prev().css({"z-index": this.props.zIndex});
			}
		}
	}
});
