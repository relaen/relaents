import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {ChannelCfg} from './channelcfg'

@Entity("t_channel_log",'tunnel')
export class ChannelLog extends BaseEntity{
	@Id()
	@Column({
		name:'channel_log_id',
		type:'int',
		nullable:false
	})
	private channelLogId:number;

	@ManyToOne({entity:'ChannelCfg',eager:false})
	@JoinColumn({name:'channel_cfg_id',refName:'channel_cfg_id'})
	private channelCfg:ChannelCfg;

	@Column({
		name:'value',
		type:'double',
		nullable:true
	})
	private value:number;

	public getChannelLogId():number{
		return this.channelLogId;
	}
	public setChannelLogId(value:number){
		this.channelLogId = value;
	}

	public getChannelCfg():ChannelCfg{
		return this.channelCfg;
	}
	public setChannelCfg(value:ChannelCfg){
		this.channelCfg = value;
	}

	public getValue():number{
		return this.value;
	}
	public setValue(value:number){
		this.value = value;
	}

}