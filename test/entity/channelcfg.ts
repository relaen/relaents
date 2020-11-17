import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Device} from './device'
import {DeviceChannel} from './devicechannel'
import {ChannelLog} from './channellog'

@Entity("t_channel_cfg",'tunnel')
export class ChannelCfg extends BaseEntity{
	@Id()
	@Column({
		name:'channel_cfg_id',
		type:'int',
		nullable:false
	})
	private channelCfgId:number;

	@ManyToOne({entity:'Device',eager:false})
	@JoinColumn({name:'child_devide_id',refName:'devide_id'})
	private deviceForChildDevideId:Device;

	@ManyToOne({entity:'Device',eager:false})
	@JoinColumn({name:'parent_devide_id',refName:'devide_id'})
	private deviceForParentDevideId:Device;

	@ManyToOne({entity:'DeviceChannel',eager:false})
	@JoinColumn({name:'device_channel_id',refName:'device_channel_id'})
	private deviceChannel:DeviceChannel;

	@Column({
		name:'parent_channel_no',
		type:'int',
		nullable:true
	})
	private parentChannelNo:number;

	@Column({
		name:'child_channel_no',
		type:'int',
		nullable:true
	})
	private childChannelNo:number;

	@OneToMany({entity:'ChannelLog',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'channelCfg',eager:false})
	private channelLogs:Array<ChannelLog>;

	public getChannelCfgId():number{
		return this.channelCfgId;
	}
	public setChannelCfgId(value:number){
		this.channelCfgId = value;
	}

	public getDeviceForChildDevideId():Device{
		return this.deviceForChildDevideId;
	}
	public setDeviceForChildDevideId(value:Device){
		this.deviceForChildDevideId = value;
	}

	public getDeviceForParentDevideId():Device{
		return this.deviceForParentDevideId;
	}
	public setDeviceForParentDevideId(value:Device){
		this.deviceForParentDevideId = value;
	}

	public getDeviceChannel():DeviceChannel{
		return this.deviceChannel;
	}
	public setDeviceChannel(value:DeviceChannel){
		this.deviceChannel = value;
	}

	public getParentChannelNo():number{
		return this.parentChannelNo;
	}
	public setParentChannelNo(value:number){
		this.parentChannelNo = value;
	}

	public getChildChannelNo():number{
		return this.childChannelNo;
	}
	public setChildChannelNo(value:number){
		this.childChannelNo = value;
	}

	public getChannelLogs():Array<ChannelLog>{
		return this.channelLogs;
	}
	public setChannelLogs(value:Array<ChannelLog>){
		this.channelLogs = value;
	}

}