import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {DeviceModel} from './devicemodel'
import {ChannelCfg} from './channelcfg'

@Entity("t_device_channel",'tunnel')
export class DeviceChannel extends BaseEntity{
	@Id()
	@Column({
		name:'device_channel_id',
		type:'int',
		nullable:false
	})
	private deviceChannelId:number;

	@ManyToOne({entity:'DeviceModel',eager:false})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	private deviceModel:DeviceModel;

	@Column({
		name:'channel_use',
		type:'string',
		nullable:true
	})
	private channelUse:string;

	@Column({
		name:'show_txt',
		type:'string',
		nullable:true
	})
	private showTxt:string;

	@OneToMany({entity:'ChannelCfg',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceChannel',eager:false})
	private channelCfgs:Array<ChannelCfg>;

	public getDeviceChannelId():number{
		return this.deviceChannelId;
	}
	public setDeviceChannelId(value:number){
		this.deviceChannelId = value;
	}

	public getDeviceModel():DeviceModel{
		return this.deviceModel;
	}
	public setDeviceModel(value:DeviceModel){
		this.deviceModel = value;
	}

	public getChannelUse():string{
		return this.channelUse;
	}
	public setChannelUse(value:string){
		this.channelUse = value;
	}

	public getShowTxt():string{
		return this.showTxt;
	}
	public setShowTxt(value:string){
		this.showTxt = value;
	}

	public getChannelCfgs():Array<ChannelCfg>{
		return this.channelCfgs;
	}
	public setChannelCfgs(value:Array<ChannelCfg>){
		this.channelCfgs = value;
	}

}