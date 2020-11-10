
import {DeviceModel} from './devicemodel'
import {ChannelCfg} from './channelcfg'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_device_channel",'tunnel')
export class DeviceChannel extends BaseEntity{
	@Id()
	@Column({
		name:'device_channel_id',
		type:'int',
		nullable:false
	})
	deviceChannelId:number;

	@ManyToOne({entity:DeviceChannel,lazyFetch:true})
	@JoinColumn({name:'device_model_id',refName:'device_model_id'})
	deviceModel:DeviceModel;

	@Column({
		name:'channel_use',
		type:'string',
		nullable:true
	})
	channelUse:string;

	@Column({
		name:'show_txt',
		type:'string',
		nullable:true
	})
	showTxt:string;

	@OneToMany({entity:'ChannelCfg',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'deviceChannel',lazyFetch:true})
	channelCfgs:Array<ChannelCfg>;

}